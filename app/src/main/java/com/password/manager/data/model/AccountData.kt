package com.password.manager.data.model

import com.password.manager.data.db.entities.AccountDbEntity
import java.util.UUID

data class AccountData(
    val id: String = UUID.randomUUID().toString(),
    val url: String = "",
    val iconUrl: String = "",
    val login: String = "",
    val password: String = ""
) {

    fun toDbEntity(encryptedPassword: String) = AccountDbEntity(
        id = id,
        website = url,
        login = login,
        password = encryptedPassword,
        faviconUrl = iconUrl
    )
}
