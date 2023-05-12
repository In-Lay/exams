package com.inlay.exams.data.dataStore.repo

import com.inlay.exams.data.dataStore.LoginDataStoreModel
import kotlinx.coroutines.flow.Flow

interface LoginDataStoreRepo {

    suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel)

    fun getLoginData(): Flow<LoginDataStoreModel?>
}