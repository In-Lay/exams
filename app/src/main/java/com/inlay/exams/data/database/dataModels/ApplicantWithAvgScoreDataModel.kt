package com.inlay.exams.data.database.dataModels

import androidx.room.Embedded
import com.inlay.exams.data.database.entities.Applicant

data class ApplicantWithAvgScoreDataModel(
    @Embedded
    val applicant: Applicant?,
    val averageScore: Int
)
