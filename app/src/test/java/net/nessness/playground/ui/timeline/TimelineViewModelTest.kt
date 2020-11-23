package net.nessness.playground.ui.timeline

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import net.nessness.playground.data.api.entity.TimelineItem
import net.nessness.playground.data.repository.TimelineRepository
import net.nessness.playground.data.repository.di.DataRepositoryModule
import net.nessness.playground.observeForTest
import net.nessness.playground.usecase.timeline.TimelineUseCase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DataRepositoryModule::class)
@Config(application = HiltTestApplication::class, sdk = [28])
@RunWith(AndroidJUnit4::class)
class TimelineViewModelTest {

    // https://dagger.dev/hilt/testing.html#hilt-rule-order
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val scope = TestCoroutineScope(dispatcher)

    // BindValue replaces provided value on dependency graph
    // lateinit for BindValue https://github.com/google/dagger/issues/2038
    @BindValue
    @MockK
    lateinit var repository: TimelineRepository

    @Inject
    lateinit var useCase: TimelineUseCase

    lateinit var viewModel: TimelineViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)

        // Cannot directly inject https://github.com/google/dagger/issues/1973
        viewModel = TimelineViewModel(useCase)
    }

    @After
    fun tearDown() {
        scope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun testUiModelEmitsTimelineSuccessfully() = scope.runBlockingTest {
        coEvery { repository.getTimeline("All") } returns List(10) { item(it) }

        viewModel.uiModel.observeForTest().apply {
            // It does not have values until category set
            assertNoValues()

            // Set category to fetch timeline
            viewModel.setCategory("All")

            // Loading at first
            assertValueAt(0) {
                assertTrue(it.loading)
            }
            // Then timeline data arrives
            assertValueAt(1) {
                assertFalse(it.loading)
                assertFalse(it.error)
                it.data.let {
                    assertEquals(10, it.items.size)
                    assertEquals("mmen0", it.items.first().id)
                    assertEquals("mmen9", it.items.last().id)
                    // fail()
                }
            }
        }
    }


    @Test
    fun testUiModelForTimelineError() = scope.runBlockingTest {
        coEvery { repository.getTimeline("All") } throws IOException("no connection")

        viewModel.uiModel.observeForTest().apply {
            // It does not have values until category set
            assertNoValues()
            // Set category to fetch timeline
            viewModel.setCategory("All")

            // Loading at first
            assertValueAt(0) {
                assertTrue(it.loading)
            }
            // Then timeline data arrives
            assertValueAt(1) {
                assertTrue(it.error)
                // fail()
            }
        }
    }

    companion object {
        private val statuses = listOf("on_sale", "sold_out")

        private fun item(i: Int): TimelineItem {
            return TimelineItem(
                "mmen$i",
                "men$i",
                status = statuses[i % 2],
                numLikes = i * 10,
                numComments = i,
                price = i * 100,
                photo = "https://$i.jpg"
            )
        }
    }
}
