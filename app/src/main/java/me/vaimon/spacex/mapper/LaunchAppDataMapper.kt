package me.vaimon.spacex.mapper

import me.vaimon.spacex.data.models.LaunchData
import me.vaimon.spacex.ui.models.Launch
import javax.inject.Inject

class LaunchAppDataMapper @Inject constructor() : Mapper<Launch, LaunchData> {
    override fun from(e: LaunchData): Launch {
        return Launch(
            e.id,
            e.name,
            e.links.missionPatchUrl
        )
    }

    override fun to(t: Launch): LaunchData {
        throw IllegalStateException()
    }
}