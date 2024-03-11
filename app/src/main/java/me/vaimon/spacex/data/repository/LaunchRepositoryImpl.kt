package me.vaimon.spacex.data.repository

import io.reactivex.rxjava3.core.Observable
import me.vaimon.spacex.data.datasouce.ApiDataSource
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.Launch
import me.vaimon.spacex.ui.repository.LaunchRepository
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val launchAppDataMapper: Mapper<Launch, LaunchData>
) : LaunchRepository {
    override val launches: Observable<List<Launch>> =
        apiDataSource.getLaunches().map {
            it.map {
                launchAppDataMapper.from(it)
            }
        }
}