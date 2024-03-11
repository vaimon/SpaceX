package me.vaimon.spacex.data.models

import com.google.gson.annotations.SerializedName

data class LaunchLinksData(
    @SerializedName("mission_patch") val missionPatchUrl: String?,
    @SerializedName("video_link") val videoUrl: String?,
    @SerializedName("wikipedia") val wikipediaUrl: String?,
)
