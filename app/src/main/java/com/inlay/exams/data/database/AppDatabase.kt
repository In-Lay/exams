package com.inlay.exams.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inlay.exams.data.database.dao.EnrollmentDao
import com.inlay.exams.data.database.entities.Applicant
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Faculty
import com.inlay.exams.data.database.entities.Grade
import com.inlay.exams.data.database.entities.Teacher

@Database(
    entities = [Faculty::class, Applicant::class, Exam::class, Teacher::class, Grade::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun enrollmentDao(): EnrollmentDao
}