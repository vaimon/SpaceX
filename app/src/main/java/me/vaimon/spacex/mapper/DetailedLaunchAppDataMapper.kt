package me.vaimon.spacex.mapper

import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.ui.models.DetailedLaunch
import javax.inject.Inject

class DetailedLaunchAppDataMapper @Inject constructor() : Mapper<DetailedLaunch, LaunchData> {
    override fun from(e: LaunchData): DetailedLaunch {
        return DetailedLaunch(
            e.id,
            e.name,
            e.year,
            e.success,
            e.details,
            e.links.videoUrl,
            e.links.missionPatchUrl,
        )
    }

    override fun to(t: DetailedLaunch): LaunchData {
        throw IllegalStateException()
    }
}