package me.vaimon.spacex.data.models

import com.google.gson.annotations.SerializedName

data class LaunchData(
    @SerializedName("flight_number") val id: Int,
    @SerializedName("mission_name") val name: String,
    @SerializedName("launch_year") val year: String,
    @SerializedName("launch_success") val success: Boolean,
    val details: String,
    val links: LaunchLinksData
)
