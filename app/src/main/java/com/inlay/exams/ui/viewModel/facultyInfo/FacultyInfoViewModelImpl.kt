package com.inlay.exams.ui.viewModel.facultyInfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.domain.mappers.mapApplicantsWithAvgScoreToUiModel
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FacultyInfoViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _faculty = MutableStateFlow(Faculty(0, ""))
    val faculty: StateFlow<Faculty> = _faculty

    private val _applicantsWithAvgScore = MutableStateFlow<List<ApplicantWithAvgScoreUiModel>>(
        mutableListOf()
    )
    val applicantsWithAvgScore: StateFlow<List<ApplicantWithAvgScoreUiModel>> =
        _applicantsWithAvgScore


    fun getFaculty() {
        viewModelScope.launch {
            _faculty.value = useCases.getFaculty()
        }
    }

    init {
        viewModelScope.launch {
            useCases.getEnlistedApplicantsWithAvgScore().collect {
                _applicantsWithAvgScore.value = it.map { dataModel ->
                    mapApplicantsWithAvgScoreToUiModel(dataModel)
                }
            }
        }
    }

//    @AssistedFactory
//    interface Factory : AssistedSavedStateViewModelFactory<FacultyInfoViewModelImpl>
}