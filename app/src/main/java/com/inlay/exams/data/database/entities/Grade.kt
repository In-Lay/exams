package com.inlay.exams.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Applicant::class,
            parentColumns = ["applicantId"],
            childColumns = ["gradeApplicantId"]
        ),
        ForeignKey(
            entity = Exam::class,
            parentColumns = ["examId"],
            childColumns = ["gradeExamId"]
        ),
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["teacherId"],
            childColumns = ["gradeTeacherId"]
        ),
    ]
)
data class Grade(
    @PrimaryKey(autoGenerate = true)
    val gradeId: Int,
    val gradeApplicantId: Int,
    val gradeExamId: Int,
    val gradeTeacherId: Int,
    val score: Int
)
