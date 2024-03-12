package me.vaimon.spacex

import android.app.Application
import me.vaimon.spacex.di.ApplicationComponent
import me.vaimon.spacex.di.DaggerApplicationComponent

class SpaceXApp : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}