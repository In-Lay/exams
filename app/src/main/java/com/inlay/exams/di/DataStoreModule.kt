package com.inlay.exams.di

import android.app.Application
import android.content.Context
import com.inlay.exams.data.dataStore.LoginDataStore
import com.inlay.exams.data.dataStore.LoginDataStoreImpl
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepo
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepoImpl
import com.inlay.exams.domain.dataStore.GetDataStore
import com.inlay.exams.domain.dataStore.GetDataStoreImpl
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.dataStore.SaveDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideLoginDataStore(application: Application): LoginDataStore {
        return LoginDataStoreImpl(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideLoginDataStoreRepo(loginDataStore: LoginDataStore): LoginDataStoreRepo {
        return LoginDataStoreRepoImpl(loginDataStore)
    }

    @Provides
    @Singleton
    fun provideGetDataStore(loginDataStoreRepo: LoginDataStoreRepo): GetDataStore {
        return GetDataStoreImpl(loginDataStoreRepo)
    }

    @Provides
    @Singleton
    fun provideSaveDataStore(loginDataStoreRepo: LoginDataStoreRepo): SaveDataStore {
        return SaveDataStoreImpl(loginDataStoreRepo)
    }
}
