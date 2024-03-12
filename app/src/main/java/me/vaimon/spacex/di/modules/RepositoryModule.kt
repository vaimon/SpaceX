package me.vaimon.spacex.di.modules

import dagger.Binds
import dagger.Module
import me.vaimon.spacex.data.repository.LaunchRepositoryImpl
import me.vaimon.spacex.ui.repository.LaunchRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideLaunchRepository(
        repo: LaunchRepositoryImpl
    ): LaunchRepository
}