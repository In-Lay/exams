package com.inlay.exams.domain.mappers

import com.inlay.exams.data.database.dataModels.ApplicantWithAvgScoreDataModel
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel

fun mapApplicantsWithAvgScoreToUiModel(dataModel: ApplicantWithAvgScoreDataModel): ApplicantWithAvgScoreUiModel {
    return ApplicantWithAvgScoreUiModel(dataModel.applicant, dataModel.averageScore)
}
