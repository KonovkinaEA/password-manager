package com.password.manager.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.password.manager.data.db.entities.AccountDbEntity

@Dao
interface AccountDao {

    @Query("SELECT account.id, website, login, password, favicon_url FROM account")
    fun getAllAccounts(): List<AccountDbEntity>

    @Insert(entity = AccountDbEntity::class)
    fun insertAccount(account: AccountDbEntity)

    @Update(entity = AccountDbEntity::class)
    fun updateAccount(account: AccountDbEntity)

    @Query("DELETE FROM account WHERE id = :id")
    fun deleteAccount(id: String)
}
