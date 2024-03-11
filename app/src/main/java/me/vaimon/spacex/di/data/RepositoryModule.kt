package me.vaimon.spacex.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.spacex.data.datasouce.ApiDataSource
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.data.repository.LaunchRepositoryImpl
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.Launch
import me.vaimon.spacex.ui.repository.LaunchRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideLaunchRepository(
        apiDataSource: ApiDataSource,
        launchAppDataMapper: Mapper<Launch, LaunchData>
    ): LaunchRepository {
        return LaunchRepositoryImpl(
            apiDataSource
        )
    }
}