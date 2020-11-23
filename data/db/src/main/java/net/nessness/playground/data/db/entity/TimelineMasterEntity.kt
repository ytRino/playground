package net.nessness.playground.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeline_master")
data class TimelineMasterEntity(
    @PrimaryKey
    val name: String,
    val data: String
)
