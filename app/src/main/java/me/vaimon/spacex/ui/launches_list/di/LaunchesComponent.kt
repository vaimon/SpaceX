package me.vaimon.spacex.ui.launches_list.di

import dagger.Subcomponent
import me.vaimon.spacex.ui.launches_list.LaunchesFragment

@Subcomponent(modules = [LaunchesModule::class])
interface LaunchesComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LaunchesComponent
    }

    fun inject(fragment: LaunchesFragment)
}