package me.vaimon.spacex.ui.repository

import io.reactivex.rxjava3.core.Observable
import me.vaimon.spacex.ui.models.Launch

interface LaunchRepository {
    val launches: Observable<List<Launch>>
}