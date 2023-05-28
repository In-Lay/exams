package com.inlay.exams.ui.viewModel.exams

import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.ui.viewModel.LoginViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ExamsViewModel @Inject constructor(
    loginViewModel: LoginViewModel,
    private val useCases: UseCases
) : ViewModel() {
//    @Inject
//    lateinit var loginViewModel: LoginViewModel

    private val _isLogged = loginViewModel.loginScreenState
     val isLogged: State<Boolean> = _isLogged

    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
     val exams: StateFlow<List<Exam>> = _exams

    private val _applicant = loginViewModel.applicant
     val applicant: StateFlow<Applicant?> = _applicant

    private val _teachers = MutableStateFlow<List<Teacher>>(emptyList())
     val teachers: StateFlow<List<Teacher>> = _teachers

    private val _passedExams = MutableStateFlow<List<Exam>>(emptyList())
     val passedExams: StateFlow<List<Exam>> = _passedExams

    private val _availableExams = MutableStateFlow<List<Exam>>(emptyList())
     val availableExams: StateFlow<List<Exam>> = _availableExams

    private val _gradeScore = MutableStateFlow(0)
     val gradeScore: StateFlow<Int> = _gradeScore

    private val _avgScore = MutableStateFlow(0)
     val avgScore: StateFlow<Int> = _avgScore

     fun issueGrade(applicant: Applicant, exam: Exam, teacher: Teacher?) {
        _gradeScore.value = Random.nextInt(100, 200)
        viewModelScope.launch {
            if (teacher == null) useCases.issueGrade(
                applicant, exam, _teachers.value[Random.nextInt(0, 4)], _gradeScore.value
            )
            else useCases.issueGrade(applicant, exam, teacher, _gradeScore.value)
        }
    }

     fun calculateAverage(applicantId: Int) {
        viewModelScope.launch {
            _avgScore.value = useCases.getAverageScore(applicantId)
        }
    }

     fun getAllExams() {
        viewModelScope.launch {
            useCases.getAllExams().collect {
                _exams.value = it
            }
        }
    }

     fun getPassedExams(applicantId: Int) {
        viewModelScope.launch {
            useCases.getPassedExamsById(applicantId).collect {
                _passedExams.value = it
            }
        }
    }

     fun getAvailableExams() {
        _availableExams.value = _exams.value.filter { exam -> !_passedExams.value.contains(exam) }
    }

     fun filterMenuItems() {
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

//    @AssistedFactory
//    interface Factory : AssistedSavedStateViewModelFactory<ExamsViewModelImpl>
}