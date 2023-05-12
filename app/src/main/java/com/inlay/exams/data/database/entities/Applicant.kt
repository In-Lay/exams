package com.inlay.exams.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Applicant(
    @PrimaryKey(autoGenerate = true)
    val applicantId: Int,
    val firstName: String,
    val lastName: String
)
