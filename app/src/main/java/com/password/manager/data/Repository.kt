package com.password.manager.data

import com.password.manager.data.model.AccountData
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val accounts: StateFlow<List<AccountData>>
    suspend fun isMasterPasswordSet(): Boolean
    suspend fun setMasterPassword(password: String)
    suspend fun isMasterPasswordCorrect(password: String): Boolean
    suspend fun getAccountData(id: String): AccountData?
}
