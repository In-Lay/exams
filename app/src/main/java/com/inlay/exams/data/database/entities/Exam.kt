package com.inlay.exams.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exam(
    @PrimaryKey(autoGenerate = true)
    val examId: Int,
    val name: String
)
