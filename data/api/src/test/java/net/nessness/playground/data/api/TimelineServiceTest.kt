package net.nessness.playground.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.ExperimentalSerializationApi
import net.nessness.playground.data.api.di.DataApiModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.create

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class TimelineServiceTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mockWebServer.shutdown()
    }

    @Test
    fun testDeserializeMaster() = runBlocking {
        mockWebServer.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(TestData.master)
        })

        val timelineService = createTimelineService()

        val result = timelineService.getTimelineMaster()

        assertEquals(3, result.size)
        assertEquals(listOf("All", "Men", "Women"), result.map { it.name })
    }

    @Test
    fun testDeserializeItem() = runBlocking {
        mockWebServer.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(TestData.items)
        })

        val timelineService = createTimelineService()

        val result = timelineService.getTimeline("")

        assertEquals(7, result.size)
        assertEquals("mmen1", result.first().id)
        assertEquals("mmen7", result.last().id)
    }

    private fun createTimelineService(): TimelineService {
        return DataApiModule.provideRetrofit()
            .newBuilder()
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()
    }
}
