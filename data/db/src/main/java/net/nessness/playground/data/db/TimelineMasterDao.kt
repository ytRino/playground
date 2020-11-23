package net.nessness.playground.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import net.nessness.playground.data.db.entity.TimelineMasterEntity

@Dao
interface TimelineMasterDao {

    @Transaction
    suspend fun invalidateWith(list: List<TimelineMasterEntity>) {
        deleteAll()
        insertAll(list)
    }

    @Insert
    suspend fun insertAll(list: List<TimelineMasterEntity>)

    @Query("select count(name) from timeline_master")
    suspend fun count(): Int

    @Query("select * from timeline_master")
    suspend fun findAll(): List<TimelineMasterEntity>

    @Query("select data from timeline_master where name = :name")
    suspend fun findDataByName(name: String): String

    @Query("delete from timeline_master")
    suspend fun deleteAll()

}
