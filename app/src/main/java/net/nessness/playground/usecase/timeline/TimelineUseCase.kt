package net.nessness.playground.usecase.timeline

import net.nessness.playground.common.model.Result
import net.nessness.playground.data.repository.TimelineRepository
import net.nessness.playground.usecase.timeline.model.SalesStatus
import net.nessness.playground.usecase.timeline.model.TimelineItem
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import net.nessness.playground.data.api.entity.TimelineItem as ApiTimelineItem

class TimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {

    suspend fun getTimeline(category: String): Result<List<TimelineItem>> {
        return try {
            val items = timelineRepository.getTimeline(category)
            items.map { it.toModelItem() }.let { Result.Success(it) }
        } catch (e: IOException) {
            Timber.w(e)
            Result.Error(e)
        } catch (e: HttpException) {
            Timber.w(e)
            Result.Error(e)
        }
    }

    private fun ApiTimelineItem.toModelItem() = TimelineItem(
        id = id,
        name = name,
        status = SalesStatus(status),
        numLikes = numLikes,
        numComments = numComments,
        price = price,
        photo = photo
    )
}
