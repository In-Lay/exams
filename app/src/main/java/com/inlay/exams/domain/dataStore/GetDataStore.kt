package com.inlay.exams.domain.dataStore

import com.inlay.exams.data.dataStore.LoginDataStoreModel
import kotlinx.coroutines.flow.Flow

interface GetDataStore {

    fun getLoginData(): Flow<LoginDataStoreModel?>
}