package com.password.manager.ui.util

import com.password.manager.data.model.SiteAccount

val accounts = listOf(
    SiteAccount(
        url = "https://github.com/",
        iconUrl = "https://github.com/favicon.ico",
        login = "login",
        encryptedPassword = "12345"
    ),
    SiteAccount(
        url = "https://vk.com/",
        iconUrl = "https://vk.com/favicon.ico",
        login = "login",
        encryptedPassword = "qwerty"
    ),
    SiteAccount(
        url = "https://www.youtube.com/",
        iconUrl = "https://www.youtube.com/favicon.ico",
        login = "login",
        encryptedPassword = "0987654321"
    ),
    SiteAccount(
        url = "https://e.mail.ru/",
        iconUrl = "https://e.mail.ru/favicon.ico",
        login = "login",
        encryptedPassword = "password"
    ),
    SiteAccount(
        url = "https://disk.yandex.ru/",
        iconUrl = "https://disk.yandex.ru/favicon.ico",
        login = "login",
        encryptedPassword = "werystrongpassword"
    )
)
