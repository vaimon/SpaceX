package me.vaimon.spacex.ui.repository

import io.reactivex.rxjava3.core.Observable
import me.vaimon.spacex.data.models.LaunchData

interface LaunchRepository {
    val launches: Observable<List<LaunchData>>
}