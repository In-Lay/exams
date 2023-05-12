package com.inlay.exams.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.loginDataStore: DataStore<Preferences> by preferencesDataStore(name = "applicant")