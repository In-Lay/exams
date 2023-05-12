package com.inlay.exams.domain.dataStore

import com.inlay.exams.data.dataStore.LoginDataStoreModel

interface SaveDataStore {

    suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel)
}