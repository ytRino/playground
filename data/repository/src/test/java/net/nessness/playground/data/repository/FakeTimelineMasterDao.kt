package net.nessness.playground.data.repository

import net.nessness.playground.data.db.TimelineMasterDao
import net.nessness.playground.data.db.entity.TimelineMasterEntity

class FakeTimelineMasterDao : TimelineMasterDao {

    private val list = mutableListOf<TimelineMasterEntity>()

    override suspend fun insertAll(list: List<TimelineMasterEntity>) {
        this.list.addAll(list)
    }

    override suspend fun count(): Int {
        return list.size
    }

    override suspend fun findAll(): List<TimelineMasterEntity> {
        return list
    }

    override suspend fun findDataByName(name: String): String {
        return list.find { it.name == name }?.name.orEmpty()
    }

    override suspend fun deleteAll() {
        list.clear()
    }
}
