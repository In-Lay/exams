package com.inlay.exams.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//class MainApplication : Application() {
//    override fun onCreate() {
//        super.onCreate()
//        startKoin {
//            androidContext(this@MainApplication)
//            modules(appModule)
//        }
//    }
//}

@HiltAndroidApp
class MainApplication: Application() {}