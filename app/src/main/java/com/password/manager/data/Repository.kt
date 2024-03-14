package com.password.manager.data

import com.password.manager.data.model.Account
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val accounts: StateFlow<List<Account>>
    fun isMasterPasswordSet(): Boolean
    fun setMasterPassword(password: String)
    fun isMasterPasswordCorrect(password: String): Boolean
}
