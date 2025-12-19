package ru.cruning.calendar.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.usecases.CalendarUseCase
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarUseCase: CalendarUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<CalendarState>(CalendarState.Loading)
    val state: State<CalendarState> = _state

    init {
        calendarUseCase.observe.onEach { calendar ->
            val money = getSalary() / calendar.size
            _state.value = CalendarState.Data(
                month = Month.March,
                year = 2025,
                list = calendar.map {
                    DayUi(
                        dayOfTheWeek = it.dayOfTheWeek,
                        dayOfMonth = it.dayOfMonth,
                        money = money,
                        isToday = false,
                        isSelected = false,
                    )
                }
            )
        }.launchIn(viewModelScope)
        calendarUseCase(
            args = CalendarUseCase.Args(
                month = Month.March,
                year = 2025,
            )
        )
    }

    //todo создать intents для этого экрана
    fun selectDay(day: DayUi) {
        when (val qwer = state.value) {
            is CalendarState.Data -> {
                _state.value = qwer.copy(
                    list = qwer.list.map {
                        if (day == it) {
                            day.copy(
                                isSelected = !it.isSelected
                            )
                        } else {
                            it.copy(
                                isSelected = false
                            )
                        }
                    }
                )
            }

            CalendarState.Error -> {}
            CalendarState.Loading -> {}
        }
    }

    //todo вынести UseCase
    private fun getSalary(): Float {
        return 100000f
    }

}

sealed interface CalendarState {
    data object Loading : CalendarState

    data class Data(
        val month: Month,
        val year: Int,
        val list: List<DayUi>,
    ) : CalendarState

    data object Error : CalendarState
}