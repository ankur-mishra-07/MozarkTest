package com.test.mozarktest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mozarktest.common.Loading
import com.test.mozarktest.common.NetworkError
import com.test.mozarktest.common.Success
import com.test.mozarktest.common.ViewState
import com.test.mozarktest.model.UserListResponse
import com.test.mozarktest.model.UserResult
import com.test.mozarktest.network.repository.DataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetcherViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    private var mUserList: MutableLiveData<ViewState<UserListResponse>> = MutableLiveData()

    private val selectedUser = MutableLiveData<UserResult>()

    fun getUserList(): MutableLiveData<ViewState<UserListResponse>> {
        return mUserList
    }


    fun getSelectedUser(): MutableLiveData<UserResult> {
        return selectedUser
    }

    private fun onError(throwable: Throwable) {
        mUserList.value = NetworkError(throwable.localizedMessage.toString())
    }


    fun fetchUserList(page: Int) {
        mUserList.value = Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        // viewModelScope launch the new coroutine on Main Dispatcher internally
        viewModelScope.launch(coroutineExceptionHandler) {
            val user = dataRepository.getUserList(page)

            mUserList.value = Success(user)
        }
    }

}