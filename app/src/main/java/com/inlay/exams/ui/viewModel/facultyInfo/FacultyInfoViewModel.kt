package com.inlay.exams.ui.viewModel.facultyInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//abstract class FacultyInfoViewModel: ViewModel() {
//    abstract val faculty: StateFlow<Faculty>
//
//    abstract val applicantsWithAvgScore: StateFlow<List<ApplicantWithAvgScoreUiModel>>
//
//    abstract fun getFaculty()
//}
//    class Factory():ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return super.create(modelClass)
//        }
//    }
