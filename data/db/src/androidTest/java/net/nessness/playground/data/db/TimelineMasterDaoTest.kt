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
import net.nessness.playground.data.db.entity.TimelineMasterEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class TimelineMasterDaoTest {

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var db: TimelineDatabase
    private lateinit var masterDao: TimelineMasterDao

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TimelineDatabase::class.java
        ).build()
        masterDao = db.timelineMasterDao
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
        masterDao.insertAll(testData1)

        val check = masterDao.findAll()
        assertEquals(listOf("All", "Men", "Women"), check.map { it.name })

        masterDao.invalidateWith(testData2)
        val result = masterDao.findAll()
        assertEquals(listOf("All", "Kids"), result.map { it.name })
    }

    @Test
    @Throws(Exception::class)
    fun testFindDataByName() = runBlocking {
        masterDao.insertAll(testData1)

        val result = masterDao.findDataByName("Men")
        assertEquals("https://men.json", result)
    }

    companion object {
        val testData1 = listOf(
            TimelineMasterEntity("All", "https://all.json"),
            TimelineMasterEntity("Men", "https://men.json"),
            TimelineMasterEntity("Women", "https://women.json")
        )
        val testData2 = listOf(
            TimelineMasterEntity("All", "https://all.json"),
            TimelineMasterEntity("Kids", "https://kids.json"),
        )
    }
}
