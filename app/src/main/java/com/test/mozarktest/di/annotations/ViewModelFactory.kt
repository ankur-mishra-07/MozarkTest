package com.test.mozarktest.di.annotations

import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Singleton
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelFactory