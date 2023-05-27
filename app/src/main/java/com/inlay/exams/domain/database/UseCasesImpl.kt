package com.inlay.exams.domain.database

import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.dataModels.ApplicantWithAvgScoreDataModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.data.database.repo.ExamsDatabaseRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class UseCasesImpl @Inject constructor(private val examsDatabaseRepo: ExamsDatabaseRepo) :
    UseCases {
    override suspend fun insertApplicant(applicant: Applicant) {
        examsDatabaseRepo.insertApplicant(applicant)
    }

    override fun getAllApplicants(): Flow<List<Applicant>> {
        return examsDatabaseRepo.getAllApplicants()
    }

    override fun getAllExams(): Flow<List<Exam>> {
        return examsDatabaseRepo.getAllExams()
    }

    override fun getAllTeachers(): Flow<List<Teacher>> {
        return examsDatabaseRepo.getAllTeachers()
    }

    override suspend fun getFaculty(): Faculty {
        return examsDatabaseRepo.getFaculty()
    }

    override suspend fun getApplicantById(applicantId: Int): Applicant? {
        return examsDatabaseRepo.getApplicantById(applicantId)
    }

    override fun getPassedExamsById(applicantId: Int): Flow<List<Exam>> {
        return examsDatabaseRepo.getPassedExamsById(applicantId)
    }

    override suspend fun getTeacherById(teacherId: Int): Teacher {
        return examsDatabaseRepo.getTeacherById(teacherId)
    }

    override suspend fun issueGrade(
        applicant: Applicant,
        exam: Exam,
        teacher: Teacher,
        score: Int
    ) {
        examsDatabaseRepo.issueGrade(applicant, exam, teacher, score)
    }

    override suspend fun getAverageScore(applicantId: Int): Int {
        return examsDatabaseRepo.getAverageScore(applicantId)
    }

    override suspend fun getEnlistedApplicants(): Flow<List<Applicant>> {
        return examsDatabaseRepo.getEnlistedApplicants()
    }

    override suspend fun getEnlistedApplicantsWithAvgScore(): Flow<List<ApplicantWithAvgScoreDataModel>> {
        return examsDatabaseRepo.getEnlistedApplicantsWithAvgScore()
    }

    override suspend fun getFullExamInfoById(applicantId: Int): List<ApplicantExamResult> {
        return examsDatabaseRepo.getFullExamInfoById(applicantId)
    }

    override suspend fun getApplicantByNameAndLastName(name: String, lastName: String): Applicant? {
        return examsDatabaseRepo.getApplicantByNameAndLastName(name, lastName)
    }
}