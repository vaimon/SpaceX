package me.vaimon.spacex.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.DetailedLaunchAppDataMapper
import me.vaimon.spacex.mapper.LaunchAppDataMapper
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.DetailedLaunch
import me.vaimon.spacex.ui.models.Launch

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindLaunchAppDataMapper(
        mapper: LaunchAppDataMapper
    ): Mapper<Launch, LaunchData>

    @Binds
    abstract fun bindDetailedLaunchAppDataMapper(
        mapper: DetailedLaunchAppDataMapper
    ): Mapper<DetailedLaunch, LaunchData>
}