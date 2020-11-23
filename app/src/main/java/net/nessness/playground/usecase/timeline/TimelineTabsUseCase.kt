package net.nessness.playground.usecase.timeline

import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.nessness.playground.common.model.Result
import net.nessness.playground.data.repository.TimelineRepository
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TimelineTabsUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {

    suspend fun getCategories(): Result<List<String>> {
        return try {
            val master = timelineRepository.getTimelineMaster()
            master.map { it.name }.let { Result.Success(it) }
        } catch (e: IOException) {
            Timber.w(e)
            Result.Error(e)
        } catch (e: HttpException) {
            Timber.w(e)
            Result.Error(e)
        }
    }
}
