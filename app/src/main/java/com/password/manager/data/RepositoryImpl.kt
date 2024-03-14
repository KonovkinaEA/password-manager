package com.password.manager.data

import com.password.manager.data.encryption.CryptoManager
import com.password.manager.data.model.SiteAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cryptoManager: CryptoManager) : Repository {

    private val _accounts: MutableStateFlow<List<SiteAccount>> = MutableStateFlow(emptyList())
    override val accounts: StateFlow<List<SiteAccount>>
        get() = _accounts.asStateFlow()

    init {
        _accounts.value = hardcodedAccounts()
    }

    override fun isMasterPasswordSet() = cryptoManager.isSecretKeySet()

    override fun setMasterPassword(password: String) {
        cryptoManager.setNewSecretKey(password)
    }

    override fun isMasterPasswordCorrect(password: String) = cryptoManager.checkSecretKey(password)

    private fun hardcodedAccounts() = listOf(
        SiteAccount(
            url = "https://github.com/",
            iconUrl = "https://github.com/favicon.ico",
            login = "login",
            encryptedPassword = cryptoManager.encrypt("12345") ?: ""
        ),
        SiteAccount(
            url = "https://vk.com/",
            iconUrl = "https://vk.com/favicon.ico",
            login = "login",
            encryptedPassword = cryptoManager.encrypt("qwerty") ?: ""
        ),
        SiteAccount(
            url = "https://www.youtube.com/",
            iconUrl = "https://www.youtube.com/favicon.ico",
            login = "login",
            encryptedPassword = cryptoManager.encrypt("0987654321") ?: ""
        ),
        SiteAccount(
            url = "https://drive.google.com/",
            iconUrl = "https://drive.google.com/favicon.ico",
            login = "login",
            encryptedPassword = cryptoManager.encrypt("password") ?: ""
        ),
        SiteAccount(
            url = "https://disk.yandex.ru/",
            iconUrl = "https://disk.yandex.ru/favicon.ico",
            login = "login",
            encryptedPassword = cryptoManager.encrypt("werystrongpassword") ?: ""
        )
    )
}
