package com.inlay.exams.ui.viewModel.profile

import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.di.getOrCreateLoginScope
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.ui.viewModel.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModelImpl(
    private val useCases: UseCases,
    private val saveDataStore: SaveDataStore
) : ProfileViewModel() {
    private val loginViewModel: LoginViewModel = getOrCreateLoginScope().get()

    private val _applicant = loginViewModel.applicant
    override val applicant = _applicant

    private val _fullExamResultById = MutableStateFlow<List<ApplicantExamResult>>(emptyList())
    override val fullExamResultById = _fullExamResultById

    private val _averageScore = MutableStateFlow<Int?>(0)
    override val averageScore = _averageScore

    private val _facultyInfoTeacher = MutableStateFlow(Teacher(0, "", ""))
    override val facultyInfoTeacher = _facultyInfoTeacher

    override fun getTeacherById(teacherId: Int) {
        viewModelScope.launch {
            _facultyInfoTeacher.value = useCases.getTeacherById(teacherId)
        }
    }

    override fun calculateAverageScore(applicantId: Int) {
        viewModelScope.launch {
            _averageScore.value = useCases.getAverageScore(applicantId)
        }
    }

    override fun getFullExamInfoById(applicantId: Int) {
        viewModelScope.launch {
            _fullExamResultById.value = useCases.getFullExamInfoById(applicantId)
        }
    }

    override fun logout() {
        viewModelScope.launch {
            saveDataStore.saveLoginData(
                LoginDataStoreModel(
                    false, 0
                )
            )
        }
    }
}