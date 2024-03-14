package com.password.manager.data

interface Repository {
    val masterPasswordSet: Boolean
    fun setMasterPassword(password: String)
    fun isMasterPasswordCorrect(password: String): Boolean
}
