package com.inlay.exams.ui.viewModel.exams

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Teacher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


abstract class ExamsViewModel: ViewModel() {
    abstract val isLogged: State<Boolean>

    abstract val applicant: StateFlow<Applicant?>

    abstract val teachers: StateFlow<List<Teacher>>

    abstract val exams: StateFlow<List<Exam>>

    abstract val passedExams: StateFlow<List<Exam>>

    abstract val availableExams: StateFlow<List<Exam>>

    abstract val gradeScore: StateFlow<Int>

    abstract val avgScore: StateFlow<Int>

    abstract fun filterMenuItems()

    abstract fun issueGrade(applicant: Applicant, exam: Exam, teacher: Teacher?)

    abstract fun calculateAverage(applicantId: Int)

    abstract fun getAllExams()

    abstract fun getPassedExams(applicantId: Int)

    abstract fun getAvailableExams()
}