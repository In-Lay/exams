package com.inlay.exams.ui.viewModel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.inlay.exams.data.database.entities.Applicant
import kotlinx.coroutines.flow.StateFlow

abstract class LoginViewModel : ViewModel() {
    abstract val loginScreenState: State<Boolean>

    abstract val applicant: StateFlow<Applicant?>
}