package com.password.manager.di

import com.password.manager.data.Repository
import com.password.manager.data.RepositoryImpl
import com.password.manager.data.encryption.CryptoManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    }
}
