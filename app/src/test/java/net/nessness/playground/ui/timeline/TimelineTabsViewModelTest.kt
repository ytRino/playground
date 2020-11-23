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
import net.nessness.playground.data.api.entity.TimelineMaster
import net.nessness.playground.data.repository.TimelineRepository
import net.nessness.playground.data.repository.di.DataRepositoryModule
import net.nessness.playground.observeForTest
import net.nessness.playground.usecase.timeline.TimelineTabsUseCase
import net.nessness.playground.usecase.timeline.model.TimelineTabsModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DataRepositoryModule::class)
@Config(application = HiltTestApplication::class, sdk = [28])
@RunWith(AndroidJUnit4::class)
class TimelineTabsViewModelTest {

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
    lateinit var useCase: TimelineTabsUseCase

    lateinit var viewModel: TimelineTabsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)

        // Cannot directly inject https://github.com/google/dagger/issues/1973
        viewModel = TimelineTabsViewModel(useCase)
    }

    @After
    fun tearDown() {
        scope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun testObserveForUiModelEmitsCategoryData(): Unit = scope.runBlockingTest {
        coEvery { repository.getTimelineMaster() } returns listOf(
            TimelineMaster("All", "https://all.json")
        )
        viewModel.uiModel.observeForTest().apply {
            advanceUntilIdle() // consume delay
            // println(values)
            assertSize(2)
            assertValueAt(0, TimelineTabsViewModel.UiModel(loading = true))
            assertValueAt(
                1,
                TimelineTabsViewModel.UiModel(_data = TimelineTabsModel(listOf("All")))
            )
        }
    }
}
