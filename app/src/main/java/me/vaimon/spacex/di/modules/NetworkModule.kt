package me.vaimon.spacex.di.modules

import dagger.Module
import dagger.Provides
import me.vaimon.spacex.data.datasouce.ApiDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiDataSource.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    @Singleton
    @Provides
    fun provideApiDataSource(retrofit: Retrofit.Builder): ApiDataSource {
        return retrofit
            .build()
            .create(ApiDataSource::class.java)
    }
}