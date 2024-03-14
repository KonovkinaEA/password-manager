package com.password.manager.data.encryption

import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {

    private val ks = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private var secretKey: Key? = null

    fun checkSecretKey(alias: String): Boolean {
        if (ks.size() == 0) {

            val keyGen = KeyGenerator.getInstance(ALGORITHM)
            keyGen.init(256)

            val key: SecretKey = keyGen.generateKey()
            val entry = KeyStore.SecretKeyEntry(key)
            val protectionParameter =
                KeyProtection.Builder(KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build()

            ks.setEntry(alias, entry, protectionParameter)
            secretKey = ks.getKey(alias, null)

            return true
        } else {
            if (ks.containsAlias(alias)) {
                secretKey = ks.getKey(alias, null)

                return true
            }
        }

        return false
    }

    fun encrypt(plainText: String): String? {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)

            val cipherText =
                Base64.encodeToString(cipher.doFinal(plainText.toByteArray()), Base64.DEFAULT)
            val iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)

            "${cipherText}.$iv"
        } catch (e: Exception) {
            null
        }
    }

    fun decrypt(cipherText: String): String? {
        val array = cipherText.split(".")
        val cipherData = Base64.decode(array[0], Base64.DEFAULT)
        val iv = Base64.decode(array[1], Base64.DEFAULT)

        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val spec = IvParameterSpec(iv)

            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

            val clearText = cipher.doFinal(cipherData)

            String(clearText, 0, clearText.size, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            null
        }
    }

    companion object {

        private const val ALGORITHM = "AES"
        private const val TRANSFORMATION = "AES/CBC/PKCS7PADDING"
    }
}
