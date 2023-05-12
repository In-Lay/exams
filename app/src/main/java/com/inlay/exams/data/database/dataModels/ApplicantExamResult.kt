package com.inlay.exams.data.database.dataModels

data class ApplicantExamResult(
    val applicantId: Int,
    val applicantFirstName: String,
    val applicantLastName: String,
    val examId: Int,
    val examName: String,
    val gradeId: Int,
    val gradeTeacherId: Int,
    val score: Int,
    val teacherFirstName: String,
    val teacherLastName: String
)
