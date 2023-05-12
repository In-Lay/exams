package com.inlay.exams.data.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object DataStoreKeys {
    val IS_LOGGED = booleanPreferencesKey("is_applicant_logged")

    val APPLICANT_ID = intPreferencesKey("applicant_id")
}