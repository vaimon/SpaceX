package me.vaimon.spacex.di.modules

import dagger.Binds
import dagger.Module
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.DetailedLaunchAppDataMapper
import me.vaimon.spacex.mapper.LaunchAppDataMapper
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.DetailedLaunch
import me.vaimon.spacex.ui.models.Launch
import javax.inject.Singleton

@Module
abstract class
MapperModule {
    @Singleton
    @Binds
    abstract fun bindLaunchAppDataMapper(
        mapper: LaunchAppDataMapper
    ): Mapper<Launch, LaunchData>

    @Singleton
    @Binds
    abstract fun bindDetailedLaunchAppDataMapper(
        mapper: DetailedLaunchAppDataMapper
    ): Mapper<DetailedLaunch, LaunchData>
}