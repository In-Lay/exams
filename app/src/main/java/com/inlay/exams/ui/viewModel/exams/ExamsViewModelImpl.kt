package com.inlay.exams.ui.viewModel.exams

import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.di.getOrCreateLoginScope
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.ui.viewModel.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ExamsViewModelImpl(
    private val useCases: UseCases
) : ExamsViewModel() {
    private val loginViewModel: LoginViewModel = getOrCreateLoginScope().get()

    private val _isLogged = loginViewModel.loginScreenState
    override val isLogged = _isLogged

    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
    override val exams = _exams

    private val _teachers = MutableStateFlow<List<Teacher>>(emptyList())
    override val teachers = _teachers

    private val _applicant = loginViewModel.applicant
    override val applicant = _applicant

    private val _passedExams = MutableStateFlow<List<Exam>>(emptyList())
    override val passedExams = _passedExams

    private val _availableExams = MutableStateFlow<List<Exam>>(emptyList())
    override val availableExams = _availableExams

    private val _gradeScore = MutableStateFlow(0)
    override val gradeScore = _gradeScore

    private val _avgScore = MutableStateFlow(0)
    override val avgScore = _avgScore

    override fun issueGrade(applicant: Applicant, exam: Exam, teacher: Teacher?) {
        _gradeScore.value = Random.nextInt(100, 200)
        viewModelScope.launch {
            if (teacher == null) useCases.issueGrade(
                applicant, exam, _teachers.value[Random.nextInt(0, 4)], _gradeScore.value
            )
            else useCases.issueGrade(applicant, exam, teacher, _gradeScore.value)
        }
    }

    override fun calculateAverage(applicantId: Int) {
        viewModelScope.launch {
            _avgScore.value = useCases.getAverageScore(applicantId)
        }
    }

    override fun getAllExams() {
        viewModelScope.launch {
            useCases.getAllExams().collect {
                _exams.value = it
            }
        }
    }

    override fun getPassedExams(applicantId: Int) {
        viewModelScope.launch {
            useCases.getPassedExamsById(applicantId).collect {
                _passedExams.value = it
            }
        }
    }

    override fun getAvailableExams() {
        _availableExams.value = _exams.value.filter { exam -> !_passedExams.value.contains(exam) }
    }

    override fun filterMenuItems() {
        _exams.value = _exams.value.filter { exam -> !_passedExams.value.contains(exam) }
    }

    init {
        viewModelScope.launch {
            useCases.getAllExams().collect {
                _exams.value = it
            }
        }

        viewModelScope.launch {
            useCases.getAllTeachers().collect {
                _teachers.value = it
            }
        }
    }
}