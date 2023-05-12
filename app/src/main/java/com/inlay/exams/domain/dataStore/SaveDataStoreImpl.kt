package com.inlay.exams.domain.dataStore

import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepo

class SaveDataStoreImpl(private val loginDataStoreRepo: LoginDataStoreRepo) : SaveDataStore {
    override suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel) {
        loginDataStoreRepo.saveLoginData(loginDataStoreModel)
    }
}