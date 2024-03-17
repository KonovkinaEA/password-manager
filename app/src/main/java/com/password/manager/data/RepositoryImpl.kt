package com.password.manager.data

import com.password.manager.data.db.AccountDao
import com.password.manager.data.encryption.CryptoManager
import com.password.manager.data.model.AccountData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cryptoManager: CryptoManager,
    private val accountDao: AccountDao
) : Repository {

    private val _accounts: MutableStateFlow<List<AccountData>> = MutableStateFlow(emptyList())
    override val accounts: StateFlow<List<AccountData>>
        get() = _accounts.asStateFlow()

    override suspend fun isMasterPasswordSet() = cryptoManager.isSecretKeySet()

    override suspend fun setMasterPassword(password: String) {
        cryptoManager.setNewSecretKey(password)
    }

    override suspend fun isMasterPasswordCorrect(password: String) =
        cryptoManager.checkSecretKey(password)

    override suspend fun loadData() {
        _accounts.value = accountDao.getAllAccounts().map {
            it.toAccount(cryptoManager.decrypt(it.password) ?: "")
        }
    }

    override suspend fun getAccount(id: String) = _accounts.value.firstOrNull { it.id == id }

    override suspend fun saveAccount(account: AccountData) {
        _accounts.update { current ->
            val updatedList = current.toMutableList()
            val existingAccount = updatedList.find { it.id == account.id }

            if (existingAccount != null) {
                updatedList.replaceAll {
                    if (it.id == account.id) account else it
                }

                accountDao.updateAccount(
                    account.toDbEntity(cryptoManager.encrypt(account.password) ?: "")
                )
            } else {
                val url = account.url
                val favicon = if (url.endsWith("/")) "favicon.ico" else "/favicon.ico"
                val accountData = account.copy(iconUrl = url + favicon)
                updatedList.add(accountData)

                accountDao.insertAccount(
                    accountData.toDbEntity(cryptoManager.encrypt(accountData.password) ?: "")
                )
            }
            updatedList.toList()
        }
    }

    override suspend fun deleteAccount(id: String) {
        _accounts.update { current ->
            current.filter { it.id != id }
        }
        accountDao.deleteAccount(id)
    }
}
