package me.vaimon.spacex.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.mapper.LaunchAppDataMapper
import me.vaimon.spacex.mapper.Mapper
import me.vaimon.spacex.ui.models.Launch

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindLaunchAppDataMapper(
        characterDomainDataMapper: LaunchAppDataMapper
    ): Mapper<Launch, LaunchData>
}