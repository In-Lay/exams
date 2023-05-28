package com.inlay.exams.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.domain.dataStore.GetDataStore
import com.inlay.exams.domain.database.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModelImpl @Inject constructor(
    getDataStore: GetDataStore,
    useCases: UseCases
) : LoginViewModel() {
    private val _loginScreenState = mutableStateOf(false)
    override val loginScreenState: State<Boolean> = _loginScreenState

    private val _applicant = MutableStateFlow<Applicant?>(null)
    override val applicant: StateFlow<Applicant?> = _applicant

    init {
        viewModelScope.launch {
            getDataStore.getLoginData().collect {
                if (it != null) {
                    _loginScreenState.value = it.loginState
                    val applicant = useCases.getApplicantById(it.applicantId)
                    _applicant.value = applicant
                } else {
                    _loginScreenState.value = false
                    _applicant.value = null
                }
            }
        }
    }
}