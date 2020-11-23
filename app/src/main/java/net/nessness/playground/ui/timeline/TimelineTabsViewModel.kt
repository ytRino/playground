package net.nessness.playground.ui.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.nessness.playground.common.model.Result
import net.nessness.playground.usecase.timeline.TimelineTabsUseCase
import net.nessness.playground.usecase.timeline.model.TimelineTabsModel

@Suppress("FunctionName")
@ExperimentalCoroutinesApi
class TimelineTabsViewModel @ViewModelInject constructor(
    private val timelineTabsUseCase: TimelineTabsUseCase
) : ViewModel() {

    val uiModel = liveData {
        emit(Loading())
//        delay(2000)  // check loading
        val result = timelineTabsUseCase.getCategories()
        when (result) {
            is Result.Success -> {
                val data = result.data
                emit(Success(TimelineTabsModel(data)))
            }
            is Result.Error -> {
                emit(Error())
            }
        }
    }

    private fun Loading() = UiModel(loading = true)
    private fun Success(data: TimelineTabsModel) = UiModel(_data = data)
    private fun Error() = UiModel(error = true)

    data class UiModel(
        val loading: Boolean = false,
        val error: Boolean = false,
        val _data: TimelineTabsModel? = null,
    ) {
        val data get() = _data!!
    }
}
