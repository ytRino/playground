package net.nessness.playground.data.repository

import net.nessness.playground.data.api.TimelineService
import net.nessness.playground.data.api.entity.TimelineItem
import net.nessness.playground.data.api.entity.TimelineMaster
import net.nessness.playground.data.db.TimelineMasterDao
import javax.inject.Inject

class TimelineRepositoryImpl @Inject constructor(
    private val timelineService: TimelineService,
    private val timelineMasterDao: TimelineMasterDao
): TimelineRepository {

    override suspend fun refreshTimelineMaster() {
        if (timelineMasterDao.count() == 0) {
            fetchTimelineMaster()
        }
    }

    private suspend fun fetchTimelineMaster() {
        val list = timelineService.getTimelineMaster()
        timelineMasterDao.invalidateWith(list.map { it.toDbEntity() })
    }

    override suspend fun getTimelineMaster(): List<TimelineMaster> {
        refreshTimelineMaster()
        return timelineMasterDao.findAll().map { it.toApiEntity() }
    }

    override suspend fun getTimeline(category: String): List<TimelineItem> {
        val url = timelineMasterDao.findDataByName(category)
        return timelineService.getTimeline(url)
    }
}
