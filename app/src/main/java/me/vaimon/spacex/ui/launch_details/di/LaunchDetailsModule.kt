package me.vaimon.spacex.ui.launch_details.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.vaimon.spacex.di.ViewModelKey
import me.vaimon.spacex.ui.launch_details.LaunchDetailsViewModel

@Module
abstract class LaunchDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchDetailsViewModel::class)
    abstract fun bindViewModel(viewmodel: LaunchDetailsViewModel): ViewModel
}