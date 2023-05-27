package com.inlay.exams.di

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
//
//@ActivityRetainedScoped
//class ViewModelProvider<VM: ViewModel> @Inject constructor(
//    private val factories: Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
//) {
//    fun get(clazz: Class<VM>, owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null): VM {
//        val factory = factories[clazz]?.create(owner, defaultArgs)
//        requireNotNull(factory) { "Unknown ViewModel class: ${clazz.name}" }
//        return ViewModelProvider(owner, factory).get(clazz)
//    }
//}