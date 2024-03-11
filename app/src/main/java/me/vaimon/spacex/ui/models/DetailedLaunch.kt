package me.vaimon.spacex.ui.models

data class DetailedLaunch(
    val id: Int,
    val name: String,
    val year: String,
    val success: Boolean,
    val details: String?,
    val youtubeUrl: String?,
    val patchUrl: String?
)
