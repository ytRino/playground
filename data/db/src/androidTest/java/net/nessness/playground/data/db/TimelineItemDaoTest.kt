package net.nessness.playground.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.nessness.playground.data.db.entity.TimelineItemEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class TimelineItemDaoTest {

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var db: TimelineDatabase
    private lateinit var itemDao: TimelineItemDao

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TimelineDatabase::class.java
        ).build()
        itemDao = db.timelineItemDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
        dispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    @Throws(Exception::class)
    fun testInvalidateWith() = runBlocking {
        itemDao.insertAll(testData1.map { create(it) })

        val check = itemDao.findAll()
        assertEquals(testData1, check.map { it.id })

        itemDao.invalidateWith(testData2.map { create(it) })
        val result = itemDao.findAll()
        assertEquals(testData2, result.map { it.id })
    }

    companion object {
        val testData1 = listOf(
            "mmen1", "mmen2", "mmen3"
        )

        val testData2 = listOf(
            "mmen42", "mmen43", "mmen44", "mmen45", "mmen46"
        )

        fun create(id: String) = TimelineItemEntity(
            id = id,
            name = "name$id",
            status = "status$id",
            numLikes = id.hashCode(),
            numComments = id.hashCode(),
            price = id.hashCode(),
            photo = "https://$id"
        )
    }
}
