package com.inlay.exams.data.database.repo

import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.dataModels.ApplicantWithAvgScoreDataModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Teacher
import kotlinx.coroutines.flow.Flow

interface ExamsDatabaseRepo {
    suspend fun insertApplicant(applicant: Applicant)

    fun getAllApplicants(): Flow<List<Applicant>>

    fun getAllExams(): Flow<List<Exam>>

    fun getAllTeachers(): Flow<List<Teacher>>

    suspend fun getFaculty(): Faculty

    suspend fun getApplicantById(applicantId: Int): Applicant?

    fun getPassedExamsById(applicantId: Int): Flow<List<Exam>>

    suspend fun getTeacherById(teacherId: Int): Teacher

    suspend fun issueGrade(applicant: Applicant, exam: Exam, teacher: Teacher, score: Int)

    suspend fun getAverageScore(applicantId: Int): Int

    suspend fun getEnlistedApplicants(): Flow<List<Applicant>>

    suspend fun getEnlistedApplicantsWithAvgScore(): Flow<List<ApplicantWithAvgScoreDataModel>>

    suspend fun getFullExamInfoById(applicantId: Int): List<ApplicantExamResult>

    suspend fun getApplicantByNameAndLastName(name: String, lastName: String): Applicant?
}