package com.inlay.exams.di

import android.app.Application
import androidx.room.Room
import com.inlay.exams.data.database.AppDatabase
import com.inlay.exams.data.database.callback.DatabaseCallback
import com.inlay.exams.data.database.dao.EnrollmentDao
import com.inlay.exams.data.database.repo.ExamsDatabaseRepo
import com.inlay.exams.data.database.repo.ExamsDatabaseRepoImpl
import com.inlay.exams.domain.dataStore.GetDataStore
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.domain.database.UseCasesImpl
import com.inlay.exams.ui.viewModel.LoginViewModel
import com.inlay.exams.ui.viewModel.LoginViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    companion object {

        @Provides
        @Singleton
        fun provideDB(application: Application?): AppDatabase {
            return Room.databaseBuilder(application!!, AppDatabase::class.java, "exams_database")
                .addCallback(
                    DatabaseCallback()
                ).build()
        }

        @Provides
        @Singleton
        fun provideDAO(appDatabase: AppDatabase): EnrollmentDao {
            return appDatabase.enrollmentDao()
        }

        @Provides
        @Singleton
        fun provideExamsDatabaseRepo(enrollmentDao: EnrollmentDao): ExamsDatabaseRepo {
            return ExamsDatabaseRepoImpl(enrollmentDao)
        }

        @Provides
        @Singleton
        fun provideUseCases(examsDatabaseRepo: ExamsDatabaseRepo): UseCases {
            return UseCasesImpl(examsDatabaseRepo)
        }

        @Provides
        @Singleton
        fun provideLoginViewModel(getDataStore: GetDataStore, useCases: UseCases): LoginViewModel {
            return LoginViewModelImpl(getDataStore, useCases)
        }
    }
}