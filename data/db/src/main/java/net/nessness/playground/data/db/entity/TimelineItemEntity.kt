package net.nessness.playground.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeline_item")
data class TimelineItemEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    val numLikes: Int,
    val numComments: Int,
    val price: Int,
    val photo: String
)
