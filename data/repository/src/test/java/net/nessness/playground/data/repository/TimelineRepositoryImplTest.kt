package net.nessness.playground.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.nessness.playground.data.api.TimelineService
import net.nessness.playground.data.api.entity.TimelineMaster
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TimelineRepositoryImplTest {

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var repository: TimelineRepositoryImpl

    private val timelineService = mockk<TimelineService>(relaxed = true)
    private val timelineMasterDao = spyk(FakeTimelineMasterDao())

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = TimelineRepositoryImpl(
            timelineService,
            timelineMasterDao
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testRefreshTimelineMasterCalled_whenNotFetched() = runBlocking {
        coEvery { timelineMasterDao.count() } returns 0
        coEvery { timelineService.getTimelineMaster() } returns listOf()

        repository.refreshTimelineMaster()

        coVerify {
            timelineService.getTimelineMaster()
            timelineMasterDao.invalidateWith(any())
        }
    }

    @Test
    fun testRefreshTimelineMasterNotCalled_whenFetched() = runBlocking {
        coEvery { timelineMasterDao.count() } returns 3
        coEvery { timelineService.getTimelineMaster() } returns listOf()

        repository.refreshTimelineMaster()

        // Verify not called
        coVerify(exactly = 0) {
            timelineService.getTimelineMaster()
            timelineMasterDao.invalidateWith(any())
        }
    }

    @Test
    fun testGetTimelineMaster_fetchAndReturnList() = runBlocking {
        val testData = listOf(
            TimelineMaster("All", "https://all.json"),
            TimelineMaster("Men", "https://men.json"),
            TimelineMaster("Women", "https://women.json")
        )

        coEvery { timelineService.getTimelineMaster() } returns testData

        val list = repository.getTimelineMaster()

        assertEquals(testData, list)
        coVerify {
            timelineService.getTimelineMaster()
            timelineMasterDao.invalidateWith(any())
            timelineMasterDao.findAll()
        }
    }
}
