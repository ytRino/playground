package net.nessness.playground.data.repository

import net.nessness.playground.data.api.entity.TimelineItem
import net.nessness.playground.data.api.entity.TimelineMaster

interface TimelineRepository {

    /**
     * Refresh master data for timelines.
     */
    suspend fun refreshTimelineMaster()

    /**
     * Get all categories for timelines.
     */
    suspend fun getTimelineMaster(): List<TimelineMaster>

    /**
     * Get timeline for specified category.
     */
    suspend fun getTimeline(category: String): List<TimelineItem>
}
