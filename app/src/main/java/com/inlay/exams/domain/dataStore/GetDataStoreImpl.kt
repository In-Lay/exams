package com.inlay.exams.domain.dataStore

import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class GetDataStoreImpl @Inject constructor(private val loginDataStoreRepo: LoginDataStoreRepo) :
    GetDataStore {

    override fun getLoginData(): Flow<LoginDataStoreModel?> {
        return loginDataStoreRepo.getLoginData()
    }
}