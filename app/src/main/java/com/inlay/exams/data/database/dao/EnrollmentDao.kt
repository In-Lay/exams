package com.inlay.exams.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.data.database.dataModels.ApplicantWithAvgScoreDataModel
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Grade
import com.inlay.exams.data.database.entities.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface EnrollmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFaculty(faculty: Faculty): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApplicant(applicant: Applicant): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun suspendInsertApplicant(applicant: Applicant): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertExam(exam: Exam): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeacher(teacher: Teacher): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGrade(grade: Grade): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun suspendInsertGrade(grade: Grade): Long

    @Query("SELECT * FROM Faculty")
    suspend fun getFaculty(): Faculty

    @Query("SELECT AVG(score) FROM Grade WHERE gradeApplicantId = :applicantId")
    suspend fun getAverageScore(applicantId: Int): Int

    @Query("SELECT * FROM Teacher WHERE teacherId = :teacherId")
    suspend fun getTeacherById(teacherId: Int): Teacher

    @Query("SELECT * FROM Applicant")
    fun getAllApplicants(): Flow<List<Applicant>>

    @Query("SELECT * FROM Applicant WHERE applicantId = :applicantId")
    suspend fun getApplicantById(applicantId: Int): Applicant?

    @Query("SELECT * FROM Applicant WHERE firstName = :name AND lastName =:lastName")
    suspend fun getApplicantByNameAndLastName(name: String, lastName: String): Applicant?

    @Query(
        """
        SELECT Applicant.*
        FROM Applicant
        INNER JOIN (SELECT gradeApplicantId, AVG(score) as averageScore
                    FROM Grade
                    GROUP BY gradeApplicantId
                    HAVING AVG(score) > 150) AS averageGrades
        ON Applicant.applicantId = averageGrades.gradeApplicantId
    """
    )
    fun getEnlistedApplicants(): Flow<List<Applicant>>

    @Query(
        "SELECT Applicant.*, AVG(Grade.score) as averageScore " +
                "FROM Applicant " +
                "INNER JOIN Grade ON Applicant.applicantId = Grade.gradeApplicantId " +
                "GROUP BY Applicant.applicantId " +
                "HAVING AVG(Grade.score) > 150 AND COUNT(Grade.gradeId) >= 3"
    )
    fun getEnlistedApplicantsWithAvgScore(): Flow<List<ApplicantWithAvgScoreDataModel>>

    @Query(
        """
        SELECT * FROM Grade
        INNER JOIN Exam ON Grade.gradeExamId = Exam.examId
        WHERE Grade.gradeApplicantId = :applicantId
    """
    )
    fun getPassedExamsByApplicantId(applicantId: Int): Flow<List<Exam>>

    @Query(
        """
        SELECT * FROM Grade
        INNER JOIN Teacher ON Grade.gradeTeacherId = Teacher.teacherId
        WHERE Grade.gradeApplicantId = :applicantId
    """
    )
    suspend fun getExaminingTeachersByApplicantId(applicantId: Int): List<Teacher>

    @Query(
        """
        SELECT Applicant.applicantId AS applicantId, Applicant.firstName AS applicantFirstName, Applicant.lastName AS applicantLastName,
               Exam.examId AS examId, Exam.name AS examName,
               Grade.gradeId AS gradeId, Grade.gradeTeacherId, Grade.score,
               Teacher.firstName AS teacherFirstName, Teacher.lastName AS teacherLastName
        FROM Grade
        INNER JOIN Applicant ON Grade.gradeApplicantId = Applicant.applicantId
        INNER JOIN Exam ON Grade.gradeExamId = Exam.examId
        INNER JOIN Teacher ON Grade.gradeTeacherId = Teacher.teacherId
        WHERE Applicant.applicantId = :applicantId
        """
    )
    suspend fun getApplicantExamResultsByApplicantId(applicantId: Int): List<ApplicantExamResult>

    @Query("SELECT * FROM Exam")
    fun getAllExams(): Flow<List<Exam>>

    @Query("SELECT * FROM Teacher")
    fun getAllTeachers(): Flow<List<Teacher>>
}