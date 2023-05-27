package com.inlay.exams.ui.viewModel.profile

import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.dataStore.LoginDataStoreModel
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.ui.viewModel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val loginViewModel: LoginViewModel,
    private val useCases: UseCases,
    private val saveDataStore: SaveDataStore
) : ProfileViewModel() {

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