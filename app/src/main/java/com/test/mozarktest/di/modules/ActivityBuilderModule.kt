package com.test.mozarktest.di.modules

import com.test.mozarktest.MainActivity
import com.test.mozarktest.di.annotations.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains all activities to be bound to application dependency graph
 */
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity

}