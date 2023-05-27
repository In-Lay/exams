package com.inlay.exams.ui.viewModel.loginScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

abstract class LoginScreenViewModel: ViewModel() {
    abstract val loginErrorText: StateFlow<String>

    abstract fun login(applicantName: String, applicantSecondName: String)

    abstract fun register(applicantName: String, applicantSecondName: String)
}