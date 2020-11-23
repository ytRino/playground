package net.nessness.playground.ui.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import net.nessness.playground.common.model.Result
import net.nessness.playground.usecase.timeline.TimelineUseCase
import net.nessness.playground.usecase.timeline.model.TimelineModel

class TimelineViewModel @ViewModelInject constructor(
    private val timelineUseCase: TimelineUseCase
) : ViewModel() {

    private var category = MutableLiveData<String>()

    val uiModel = category.distinctUntilChanged().switchMap { category ->
        liveData {
            emit(Loading())
            val result = timelineUseCase.getTimeline(category)
            when (result) {
                is Result.Success -> {
                    emit(Success(TimelineModel(result.data)))
                }
                is Result.Error -> {
                    emit(Error())
                }
            }
        }
    }

    fun setCategory(category: String) {
        this.category.postValue(category)
    }

    private fun Loading() = UiModel(loading = true)
    private fun Success(data: TimelineModel) = UiModel(_data = data)
    private fun Error() = UiModel(error = true)

    data class UiModel(
        val loading: Boolean = false,
        val error: Boolean = false,
        private val _data: TimelineModel? = null,
    ) {
        val data get() = _data!!
    }
}
