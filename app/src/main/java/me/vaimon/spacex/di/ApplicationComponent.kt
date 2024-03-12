package me.vaimon.spacex.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import me.vaimon.spacex.di.modules.MapperModule
import me.vaimon.spacex.di.modules.NetworkModule
import me.vaimon.spacex.di.modules.RepositoryModule
import me.vaimon.spacex.ui.launch_details.di.LaunchDetailsComponent
import me.vaimon.spacex.ui.launches_list.di.LaunchesComponent
import me.vaimon.spacex.ui.repository.LaunchRepository
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MapperModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun launchesComponent(): LaunchesComponent.Factory
    fun launchDetailsComponent(): LaunchDetailsComponent.Factory

    val launchRepository: LaunchRepository
}

@Module(
    subcomponents = [
        LaunchesComponent::class,
        LaunchDetailsComponent::class
    ]
)
object SubcomponentsModule