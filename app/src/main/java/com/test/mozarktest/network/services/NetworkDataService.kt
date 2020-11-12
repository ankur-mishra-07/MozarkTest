package com.test.mozarktest.network.services

import com.test.mozarktest.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkDataService {
    @GET("api/")
    suspend fun getUserList(@Query("results") page: Int): UserListResponse

}
