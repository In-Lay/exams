package com.inlay.exams.data.database.repo

import com.inlay.exams.data.database.dao.EnrollmentDao
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.dataModels.ApplicantWithAvgScoreDataModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Grade
import com.inlay.exams.data.database.entities.Teacher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class ExamsDatabaseRepoImpl @Inject constructor(private val enrollmentDao: EnrollmentDao) :
    ExamsDatabaseRepo {
    override suspend fun insertApplicant(applicant: Applicant) {
        enrollmentDao.suspendInsertApplicant(applicant)
    }

    override fun getAllApplicants(): Flow<List<Applicant>> {
        return enrollmentDao.getAllApplicants()
    }

    override fun getAllExams(): Flow<List<Exam>> {
        return enrollmentDao.getAllExams()
    }

    override fun getAllTeachers(): Flow<List<Teacher>> {
        return enrollmentDao.getAllTeachers()
    }

    override suspend fun getFaculty(): Faculty {
        return enrollmentDao.getFaculty()
    }

    override suspend fun getApplicantById(applicantId: Int): Applicant? {
        return enrollmentDao.getApplicantById(applicantId)
    }

    override fun getPassedExamsById(applicantId: Int): Flow<List<Exam>> {
        return enrollmentDao.getPassedExamsByApplicantId(applicantId)
    }

    override suspend fun getTeacherById(teacherId: Int): Teacher {
        return enrollmentDao.getTeacherById(teacherId)
    }

    override suspend fun getApplicantByNameAndLastName(name: String, lastName: String): Applicant? {
        return enrollmentDao.getApplicantByNameAndLastName(name, lastName)
    }

    override suspend fun issueGrade(
        applicant: Applicant,
        exam: Exam,
        teacher: Teacher,
        score: Int
    ) {
        val grade = Grade(0, applicant.applicantId, exam.examId, teacher.teacherId, score)

        enrollmentDao.suspendInsertGrade(grade)
    }

    override suspend fun getAverageScore(applicantId: Int): Int {
        return withContext(Dispatchers.IO) {
            enrollmentDao.getAverageScore(applicantId)
        }
    }

    override suspend fun getEnlistedApplicants(): Flow<List<Applicant>> {
        return enrollmentDao.getEnlistedApplicants()
    }

    override suspend fun getEnlistedApplicantsWithAvgScore(): Flow<List<ApplicantWithAvgScoreDataModel>> {
        return enrollmentDao.getEnlistedApplicantsWithAvgScore()
    }

    override suspend fun getFullExamInfoById(applicantId: Int): List<ApplicantExamResult> {
        return enrollmentDao.getApplicantExamResultsByApplicantId(applicantId)
    }
}