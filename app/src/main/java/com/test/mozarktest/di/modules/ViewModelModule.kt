package com.test.mozarktest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mozarktest.di.annotations.ApplicationScope
import com.test.mozarktest.di.annotations.ViewModelKey
import com.test.mozarktest.viewmodel.FetcherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides map of all ViewModels and a ViewModelFactory for dependencies
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FetcherViewModel::class)
    abstract fun bindFetcherModel(mainViewModelUser: FetcherViewModel): ViewModel

    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}