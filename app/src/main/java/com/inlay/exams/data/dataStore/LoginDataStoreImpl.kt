package com.inlay.exams.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginDataStoreImpl(private val context: Context) : LoginDataStore {
    override suspend fun saveLoginData(loginDataStoreModel: LoginDataStoreModel) {
        context.loginDataStore.edit {
            it[DataStoreKeys.IS_LOGGED] = loginDataStoreModel.loginState
            it[DataStoreKeys.APPLICANT_ID] = loginDataStoreModel.applicantId
        }
    }

    override fun getLoginData(): Flow<LoginDataStoreModel?> {
        return context.loginDataStore.data.map {
            LoginDataStoreModel(
                it[DataStoreKeys.IS_LOGGED] ?: false, it[DataStoreKeys.APPLICANT_ID] ?: 0
            )
        }
    }
}