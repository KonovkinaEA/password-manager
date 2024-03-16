package com.password.manager.di

import android.content.Context
import com.password.manager.data.Repository
import com.password.manager.data.RepositoryImpl
import com.password.manager.data.db.AppDatabase
import com.password.manager.data.encryption.CryptoManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun provideRepository(repository: RepositoryImpl): Repository

    companion object {

        @Singleton
        @Provides
        fun provideCryptoManager(): CryptoManager = CryptoManager()

        @Singleton
        @Provides
        fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Singleton
        @Provides
        fun provideAccountDao(database: AppDatabase) = database.getAccountDao()

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)
    }
}
