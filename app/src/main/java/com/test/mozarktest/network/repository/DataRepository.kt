package com.test.mozarktest.network.repository

import com.test.mozarktest.model.UserListResponse
import com.test.mozarktest.network.services.NetworkDataService
import javax.inject.Inject

class DataRepository @Inject constructor(private val mService: NetworkDataService) {
    suspend fun getUserList(page: Int): UserListResponse = mService.getUserList(page)
//    suspend fun getMovieDetail(mImDbId: String): MovieDetails = mService.getMovieDetail(mImDbId)

}
