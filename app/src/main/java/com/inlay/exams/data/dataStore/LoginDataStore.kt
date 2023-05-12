package com.inlay.exams.data.dataStore

import kotlinx.coroutines.flow.Flow

interface LoginDataStore {

    suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel)

    fun getLoginData(): Flow<LoginDataStoreModel?>
}