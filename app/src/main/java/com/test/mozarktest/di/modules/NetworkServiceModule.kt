package com.test.mozarktest.di.modules

import com.test.mozarktest.di.annotations.ApplicationScope
import com.test.mozarktest.network.services.NetworkDataService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Provides network services for data
 */
@Module(includes = [NetworkModule::class])
class NetworkServiceModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): NetworkDataService {
        return retrofit.create(NetworkDataService::class.java)
    }
}