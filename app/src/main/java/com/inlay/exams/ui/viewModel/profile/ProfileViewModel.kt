package com.inlay.exams.ui.viewModel.profile

import androidx.lifecycle.ViewModel
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Teacher
import kotlinx.coroutines.flow.StateFlow

abstract class ProfileViewModel : ViewModel() {
    abstract val applicant: StateFlow<Applicant?>

    abstract val fullExamResultById: StateFlow<List<ApplicantExamResult>>

    abstract val averageScore: StateFlow<Int?>

    abstract val facultyInfoTeacher: StateFlow<Teacher>

    abstract fun getTeacherById(teacherId: Int)

    abstract fun calculateAverageScore(applicantId: Int)

    abstract fun getFullExamInfoById(applicantId: Int)

    abstract fun logout()
}