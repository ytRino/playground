package net.nessness.playground.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import net.nessness.playground.data.db.entity.TimelineItemEntity

/**
 * May use later phase
 */
@Dao
interface TimelineItemDao {

    @Transaction
    suspend fun invalidateWith(list: List<TimelineItemEntity>) {
        deleteAll()
        insertAll(list)
    }

    @Insert
    suspend fun insertAll(list: List<TimelineItemEntity>)

    @Query("select * from timeline_item")
    suspend fun findAll(): List<TimelineItemEntity>

    @Query("delete from timeline_item")
    suspend fun deleteAll()

}
