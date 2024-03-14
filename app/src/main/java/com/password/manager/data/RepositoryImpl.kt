package com.password.manager.data

import com.password.manager.data.encryption.CryptoManager
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cryptoManager: CryptoManager) : Repository {

    override val masterPasswordSet: Boolean
        get() = cryptoManager.isSecretKeySet()

    override fun setMasterPassword(password: String) {
        cryptoManager.setNewSecretKey(password)
    }

    override fun isMasterPasswordCorrect(password: String) = cryptoManager.checkSecretKey(password)
}
