package net.nessness.playground.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.nessness.playground.data.db.entity.TimelineItemEntity
import net.nessness.playground.data.db.entity.TimelineMasterEntity

@Database(
    version = 2,
    entities = [
        TimelineMasterEntity::class,
        TimelineItemEntity::class
    ],
    exportSchema = false
)
abstract class TimelineDatabase : RoomDatabase() {

    abstract val timelineMasterDao: TimelineMasterDao

    abstract val timelineItemDao: TimelineItemDao
}
