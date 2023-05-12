package com.inlay.exams.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Faculty(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
