package me.vaimon.spacex.data.datasouce

import io.reactivex.rxjava3.core.Observable
import me.vaimon.spacex.data.models.LaunchData
import retrofit2.http.GET

interface ApiDataSource {
    companion object {
        const val BASE_URL = "https://api.spacexdata.com/v3/"
    }

    @GET("launches")
    fun getLaunches(): Observable<List<LaunchData>>
}