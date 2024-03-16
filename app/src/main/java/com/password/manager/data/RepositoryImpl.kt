package com.password.manager.data

import com.password.manager.data.encryption.CryptoManager
import com.password.manager.data.model.AccountData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cryptoManager: CryptoManager) : Repository {

    private val _accounts: MutableStateFlow<List<AccountData>> = MutableStateFlow(emptyList())
    override val accounts: StateFlow<List<AccountData>>
        get() = _accounts.asStateFlow()

    init {
        _accounts.value = hardcodedAccounts()
    }

    override suspend fun isMasterPasswordSet() = cryptoManager.isSecretKeySet()

    override suspend fun setMasterPassword(password: String) {
        cryptoManager.setNewSecretKey(password)
    }

    override suspend fun isMasterPasswordCorrect(password: String) =
        cryptoManager.checkSecretKey(password)

    override suspend fun getAccountData(id: String) = _accounts.value.firstOrNull { it.id == id }

    override suspend fun saveAccountData(accountData: AccountData) {
        _accounts.update { current ->
            val updatedList = current.toMutableList()
            val existingAccount = updatedList.find { it.id == accountData.id }
            if (existingAccount != null) {
                updatedList.replaceAll {
                    if (it.id == accountData.id) accountData else it
                }
            } else {
                val url = accountData.url
                val favicon = if (url.endsWith("/")) "favicon.ico" else "/favicon.ico"
                updatedList.add(accountData.copy(iconUrl = url + favicon))
            }
            updatedList.toList()
        }
    }

    override suspend fun deleteAccountData(id: String) {
        _accounts.update { current ->
            current.filter { it.id != id }
        }
    }

    private fun hardcodedAccounts() = listOf(
        AccountData(
            id = "1",
            url = "https://github.com/",
            iconUrl = "https://github.com/favicon.ico",
            login = "login",
            password = "12345"
        ),
        AccountData(
            url = "https://vk.com/",
            iconUrl = "https://vk.com/favicon.ico",
            login = "login",
            password = "qwerty"
        ),
        AccountData(
            url = "https://www.youtube.com/",
            iconUrl = "https://www.youtube.com/favicon.ico",
            login = "login",
            password = "0987654321"
        ),
        AccountData(
            url = "https://drive.google.com/",
            iconUrl = "https://drive.google.com/favicon.ico",
            login = "login",
            password = "password"
        ),
        AccountData(
            url = "https://disk.yandex.ru/",
            iconUrl = "https://disk.yandex.ru/favicon.ico",
            login = "login",
            password = "verystrongpassword"
        )
    )
}
