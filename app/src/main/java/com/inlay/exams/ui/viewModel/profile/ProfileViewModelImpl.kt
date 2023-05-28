package com.inlay.exams.ui.viewModel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.ui.viewModel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    loginViewModel: LoginViewModel,
    private val useCases: UseCases,
    private val saveDataStore: SaveDataStore
) : ViewModel() {

//    @Inject
//    lateinit var loginViewModel: LoginViewModel

    private val _applicant = loginViewModel.applicant
    val applicant: StateFlow<Applicant?> = _applicant

    private val _fullExamResultById = MutableStateFlow<List<ApplicantExamResult>>(emptyList())
    val fullExamResultById: StateFlow<List<ApplicantExamResult>> = _fullExamResultById

    private val _averageScore = MutableStateFlow<Int?>(0)
    val averageScore: StateFlow<Int?> = _averageScore

    private val _facultyInfoTeacher = MutableStateFlow(Teacher(0, "", ""))
    val facultyInfoTeacher: StateFlow<Teacher> = _facultyInfoTeacher

    fun getTeacherById(teacherId: Int) {
        viewModelScope.launch {
            _facultyInfoTeacher.value = useCases.getTeacherById(teacherId)
        }
    }

    fun calculateAverageScore(applicantId: Int) {
        viewModelScope.launch {
            _averageScore.value = useCases.getAverageScore(applicantId)
        }
    }

    fun getFullExamInfoById(applicantId: Int) {
        viewModelScope.launch {
            _fullExamResultById.value = useCases.getFullExamInfoById(applicantId)
        }
    }

    fun logout() {
        viewModelScope.launch {
            saveDataStore.saveLoginData(
                LoginDataStoreModel(
                    false, 0
                )
            )
        }
    }
}