package com.password.manager.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.password.manager.data.model.AccountData

@Entity(tableName = "account", indices = [Index("id")])
data class AccountDbEntity(
    @PrimaryKey val id: String,
    val website: String,
    val login: String,
    val password: String,
    @ColumnInfo(name = "favicon_url") val faviconUrl: String
) {

    fun toAccount(decryptedPassword: String) = AccountData(
        id = id,
        url = website,
        iconUrl = faviconUrl,
        login = login,
        password = decryptedPassword
    )
}
