package com.inlay.exams.data.dataStore.repo

import com.inlay.exams.data.dataStore.LoginDataStore
import com.inlay.exams.data.dataStore.LoginDataStoreModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataStoreRepoImpl @Inject constructor(private val loginDataStore: LoginDataStore) :
    LoginDataStoreRepo {

    override suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel) {
        loginDataStore.saveLoginData(loginDataStoreModel)
    }

    override fun getLoginData(): Flow<LoginDataStoreModel?> {
        return loginDataStore.getLoginData()
    }
}