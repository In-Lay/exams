package com.inlay.exams.data.database.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.inlay.exams.data.database.dao.EnrollmentDao
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Grade
import com.inlay.exams.data.database.entities.Teacher
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.random.Random

class DatabaseCallback : RoomDatabase.Callback() {
    //    private val enrollmentDao: EnrollmentDao by inject()
    @Inject
    lateinit var enrollmentDao: EnrollmentDao

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val listOfApplicants = listOf(
            Applicant(0, "George", "Saleman"),
            Applicant(0, "Darry", "Willsaint"),
            Applicant(0, "Anetta", "Cruze")
        )

        val listOfTeachers = listOf(
            Teacher(0, "Garnt", "Fuse"),
            Teacher(0, "Angela", "Lisent"),
            Teacher(0, "Mary", "Fuse"),
            Teacher(0, "Jill", "McNewrow"),
            Teacher(0, "Greg", "Hunter"),
        )

        val listOfExams = listOf(
            Exam(0, "Math"),
            Exam(0, "Physics"),
            Exam(0, "English"),
            Exam(0, "Chemistry"),
        )

        Executors.newSingleThreadExecutor().execute {
            enrollmentDao.apply {
                this.insertFaculty(Faculty(0, "Physics by Francis of the Filth"))

                val applicantsId = listOfApplicants.map {
                    this.insertApplicant(it).toInt()
                }
                listOfTeachers.forEach {
                    this.insertTeacher(it)
                }
                listOfExams.forEach {
                    this.insertExam(it)
                }

                applicantsId.forEach { id ->
                    repeat(3) {
                        insertGrade(
                            Grade(
                                0,
                                id,
                                Random.nextInt(1, 4),
                                Random.nextInt(1, 5),
                                Random.nextInt(150, 200)
                            )
                        )
                    }
                }
            }
        }
    }
}