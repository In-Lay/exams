package com.inlay.exams.ui.viewModel.loginScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.database.UseCases
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    private val saveDataStore: SaveDataStore,
) : ViewModel() {
    private val _loginError = MutableStateFlow("")
    val loginErrorText: StateFlow<String> = _loginError

    fun login(applicantName: String, applicantSecondName: String) {
        viewModelScope.launch {
            val user = useCases.getApplicantByNameAndLastName(applicantName, applicantSecondName)
            if (user != null) {
                val dataStoreModel = LoginDataStoreModel(
                    true, user.applicantId
                )
                saveDataStore.saveLoginData(dataStoreModel)
            } else
                _loginError.value = "Such user doesn't exist!"
        }
    }

    fun register(applicantName: String, applicantSecondName: String) {
        viewModelScope.launch {
            var user = useCases.getApplicantByNameAndLastName(applicantName, applicantSecondName)
            if (user != null) {
                _loginError.value = "Such user already exists!"
            } else {
                useCases.insertApplicant(Applicant(0, applicantName, applicantSecondName))
                user =
                    useCases.getApplicantByNameAndLastName(applicantName, applicantSecondName)
                saveDataStore.saveLoginData(
                    LoginDataStoreModel(
                        true, user!!.applicantId
                    )
                )
            }
        }
    }

//    @AssistedFactory
//    interface Factory : AssistedSavedStateViewModelFactory<LoginScreenViewModelImpl>
}