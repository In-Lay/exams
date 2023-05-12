package com.inlay.exams.di

import androidx.room.Room
import com.inlay.exams.data.dataStore.LoginDataStore
import com.inlay.exams.data.dataStore.LoginDataStoreImpl
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepo
import com.inlay.exams.data.dataStore.repo.LoginDataStoreRepoImpl
import com.inlay.exams.data.database.AppDatabase
import com.inlay.exams.data.database.callback.DatabaseCallback
import com.inlay.exams.data.database.repo.ExamsDatabaseRepo
import com.inlay.exams.data.database.repo.ExamsDatabaseRepoImpl
import com.inlay.exams.domain.dataStore.GetDataStore
import com.inlay.exams.domain.dataStore.GetDataStoreImpl
import com.inlay.exams.domain.dataStore.SaveDataStore
import com.inlay.exams.domain.dataStore.SaveDataStoreImpl
import com.inlay.exams.domain.database.UseCases
import com.inlay.exams.domain.database.UseCasesImpl
import com.inlay.exams.ui.viewModel.LoginViewModel
import com.inlay.exams.ui.viewModel.LoginViewModelImpl
import com.inlay.exams.ui.viewModel.exams.ExamsViewModel
import com.inlay.exams.ui.viewModel.exams.ExamsViewModelImpl
import com.inlay.exams.ui.viewModel.facultyInfo.FacultyInfoViewModel
import com.inlay.exams.ui.viewModel.facultyInfo.FacultyInfoViewModelImpl
import com.inlay.exams.ui.viewModel.loginScreen.LoginScreenViewModel
import com.inlay.exams.ui.viewModel.loginScreen.LoginScreenViewModelImpl
import com.inlay.exams.ui.viewModel.profile.ProfileViewModel
import com.inlay.exams.ui.viewModel.profile.ProfileViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

const val LOGIN_SCOPE_NAME = "LOGIN_SCOPE_NAME"
const val LOGIN_SCOPE_ID = "LOGIN_SCOPE_ID"

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "exams_database")
            .addCallback(
                DatabaseCallback()
            ).build()
    }

    single { get<AppDatabase>().enrollmentDao() }

    single<ExamsDatabaseRepo> { ExamsDatabaseRepoImpl(enrollmentDao = get()) }

    single<UseCases> { UseCasesImpl(examsDatabaseRepo = get()) }


    factory<LoginDataStore> { LoginDataStoreImpl(androidContext()) }

    single<LoginDataStoreRepo> { LoginDataStoreRepoImpl(loginDataStore = get()) }

    single<SaveDataStore> { SaveDataStoreImpl(loginDataStoreRepo = get()) }

    single<GetDataStore> { GetDataStoreImpl(loginDataStoreRepo = get()) }


    viewModel<ExamsViewModel> {
        ExamsViewModelImpl(
            useCases = get()
        )
    }

    viewModel<FacultyInfoViewModel> { FacultyInfoViewModelImpl(useCases = get()) }

    viewModel<LoginScreenViewModel> {
        LoginScreenViewModelImpl(
            useCases = get(),
            saveDataStore = get()
        )
    }

    viewModel<ProfileViewModel> { ProfileViewModelImpl(useCases = get(), saveDataStore = get()) }

    scope(named(LOGIN_SCOPE_NAME)) {
        scoped<LoginViewModel> {
            LoginViewModelImpl(getDataStore = get(), useCases = get())
        }
    }
}

fun getOrCreateLoginScope(): Scope {
    return KoinJavaComponent.getKoin().getOrCreateScope(LOGIN_SCOPE_ID, named(LOGIN_SCOPE_NAME))
}
