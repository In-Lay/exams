package com.inlay.exams.ui.viewModel.facultyInfo

import androidx.lifecycle.ViewModel
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel
import kotlinx.coroutines.flow.StateFlow

abstract class FacultyInfoViewModel : ViewModel() {
    abstract val faculty: StateFlow<Faculty>

    abstract val applicantsWithAvgScore: StateFlow<List<ApplicantWithAvgScoreUiModel>>

    abstract fun getFaculty()
}