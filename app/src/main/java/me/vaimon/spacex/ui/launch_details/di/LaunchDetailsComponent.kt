package me.vaimon.spacex.ui.launch_details.di

import dagger.Subcomponent
import me.vaimon.spacex.ui.launch_details.LaunchDetailsFragment

@Subcomponent(modules = [LaunchDetailsModule::class])
interface LaunchDetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LaunchDetailsComponent
    }

    fun inject(fragment: LaunchDetailsFragment)
}