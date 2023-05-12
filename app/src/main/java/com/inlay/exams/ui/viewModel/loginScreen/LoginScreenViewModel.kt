package com.inlay.exams.ui.viewModel.loginScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class LoginScreenViewModel : ViewModel() {
    abstract val loginErrorText: StateFlow<String>

    abstract fun login(applicantName: String, applicantSecondName: String)

    abstract fun register(applicantName: String, applicantSecondName: String)
}