package com.password.manager.data.model

import java.util.UUID

data class AccountData(
    val id: String = UUID.randomUUID().toString(),
    val url: String = "",
    val iconUrl: String = "",
    val login: String = "",
    val password: String = ""
)
