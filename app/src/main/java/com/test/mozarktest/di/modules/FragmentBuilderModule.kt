package com.test.mozarktest.di.modules

import com.test.mozarktest.ui.UserDetailsScreen
import com.test.mozarktest.ui.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserDetailsFragment(): UserDetailsScreen

}