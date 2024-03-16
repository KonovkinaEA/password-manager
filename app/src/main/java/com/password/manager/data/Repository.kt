package com.password.manager.data

import com.password.manager.data.model.AccountData
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val accounts: StateFlow<List<AccountData>>
    suspend fun isMasterPasswordSet(): Boolean
    suspend fun setMasterPassword(password: String)
    suspend fun isMasterPasswordCorrect(password: String): Boolean
    suspend fun loadData()
    suspend fun getAccount(id: String): AccountData?
    suspend fun saveAccount(account: AccountData)
    suspend fun deleteAccount(id: String)
}
