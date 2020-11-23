package net.nessness.playground.data.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class TimelineMaster(
    val name: String,
    val data: String
)
