package com.password.manager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.password.manager.data.db.entities.AccountDbEntity

@Database(version = 1, entities = [AccountDbEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDao

    companion object {

        private var instance: AppDatabase? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
                .createFromAsset("account_db.db")
                .build()
        }
    }
}
