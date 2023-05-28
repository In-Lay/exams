package com.inlay.exams.di


//@Module
//@InstallIn(SingletonComponent::class)
//abstract class ViewModelModule {
//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModelImpl::class)
//    abstract fun bindLoginViewModel(viewModel: LoginViewModelImpl): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(FacultyInfoViewModelImpl::class)
//    abstract fun bindFacultyInfoViewModel(viewModel: FacultyInfoViewModelImpl): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginScreenViewModelImpl::class)
//    abstract fun bindLoginScreenViewModel(viewModel: LoginScreenViewModelImpl): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ProfileViewModelImpl::class)
//    abstract fun bindProfileViewModel(viewModel: ProfileViewModelImpl): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ExamsViewModelImpl::class)
//    abstract fun bindExamsViewModel(viewModel: ExamsViewModelImpl): ViewModel

//    @Provides
//    fun provideFacultyInfoViewModelFactory(
//        factory: FacultyInfoViewModelImpl.Factory,
//        owner: SavedStateRegistryOwner,
//        defaultArgs: Bundle? = null
//    ): AbstractSavedStateViewModelFactory {
//        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                key: String, modelClass: Class<T>, handle: SavedStateHandle
//            ): T {
//                return factory.create(handle) as T
//            }
//        }
//    }
//
//    @Provides
//    fun provideLoginScreenViewModelFactory(
//        factory: LoginScreenViewModelImpl.Factory,
//        owner: SavedStateRegistryOwner,
//        defaultArgs: Bundle? = null
//    ): AbstractSavedStateViewModelFactory {
//        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                key: String, modelClass: Class<T>, handle: SavedStateHandle
//            ): T {
//                return factory.create(handle) as T
//            }
//        }
//    }
//
//    @Provides
//    fun provideProfileViewModelFactory(
//        factory: ProfileViewModelImpl.Factory,
//        owner: SavedStateRegistryOwner,
//        defaultArgs: Bundle? = null
//    ): AbstractSavedStateViewModelFactory {
//        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                key: String, modelClass: Class<T>, handle: SavedStateHandle
//            ): T {
//                return factory.create(handle) as T
//            }
//        }
//    }
//
//    @Provides
//    fun provideExamsViewModelFactory(
//        factory: ExamsViewModelImpl.Factory,
//        owner: SavedStateRegistryOwner,
//        defaultArgs: Bundle? = null
//    ): AbstractSavedStateViewModelFactory {
//        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                key: String, modelClass: Class<T>, handle: SavedStateHandle
//            ): T {
//                return factory.create(handle) as T
//            }
//        }
//    }
//}
