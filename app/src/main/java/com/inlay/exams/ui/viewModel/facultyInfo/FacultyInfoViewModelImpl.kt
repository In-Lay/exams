package com.inlay.exams.ui.viewModel.facultyInfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.domain.mappers.mapApplicantsWithAvgScoreToUiModel
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FacultyInfoViewModelImpl @Inject constructor(
    private val useCases: UseCases
) : FacultyInfoViewModel() {
    private val _faculty = MutableStateFlow(Faculty(0, ""))
    override val faculty = _faculty

    private val _applicantsWithAvgScore = MutableStateFlow<List<ApplicantWithAvgScoreUiModel>>(
        mutableListOf()
    )
    override val applicantsWithAvgScore = _applicantsWithAvgScore


    override fun getFaculty() {
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