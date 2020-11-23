package net.nessness.playground.data.api

import net.nessness.playground.data.api.entity.TimelineItem
import net.nessness.playground.data.api.entity.TimelineMaster
import retrofit2.http.GET
import retrofit2.http.Path

interface TimelineService {

    @GET("cats.json")
    suspend fun getTimelineMaster(): List<TimelineMaster>

    @GET("{path}")
    suspend fun getTimeline(@Path("path") path: String): List<TimelineItem>
}
