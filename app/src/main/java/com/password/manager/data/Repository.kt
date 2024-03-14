package com.password.manager.data

import com.password.manager.data.model.SiteAccount
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val accounts: StateFlow<List<SiteAccount>>
    fun isMasterPasswordSet(): Boolean
    fun setMasterPassword(password: String)
    fun isMasterPasswordCorrect(password: String): Boolean
}
