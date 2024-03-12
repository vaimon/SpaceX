package me.vaimon.spacex.ui.launches_list.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.vaimon.spacex.di.ViewModelKey
import me.vaimon.spacex.ui.launches_list.LaunchesViewModel

@Module
abstract class LaunchesModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun bindViewModel(viewmodel: LaunchesViewModel): ViewModel
}