package com.test.mozarktest

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.mozarktest.base.BaseActivity
import com.test.mozarktest.common.AppConstants
import com.test.mozarktest.model.UserResult
import com.test.mozarktest.ui.UserDetailsScreen
import com.test.mozarktest.ui.UserListFragment
import com.test.mozarktest.viewmodel.FetcherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var viewModel: FetcherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        defaultFragment(
            UserListFragment.newInstance(AppConstants.ColumnCount),
            container = R.id.mainContainer
        )
    }

    private fun initListener() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FetcherViewModel::class.java)
        viewModel.getSelectedUser().observe(this, Observer { onUserSelected(it) })
    }

    private fun onUserSelected(user: UserResult) {
        callFragmentSwitcher(
            UserDetailsScreen.newInstance(user.id.name),
            container = R.id.mainContainer
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}