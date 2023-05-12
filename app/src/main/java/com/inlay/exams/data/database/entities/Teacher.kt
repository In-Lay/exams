package com.inlay.exams.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    val teacherId: Int,
    val firstName: String,
    val lastName: String
)
