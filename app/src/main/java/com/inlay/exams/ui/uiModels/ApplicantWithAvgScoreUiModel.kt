package com.inlay.exams.ui.uiModels

import com.inlay.exams.data.database.entities.Applicant

data class ApplicantWithAvgScoreUiModel(val applicant: Applicant?, val averageScore: Int)