package ru.cruning.calendar.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.usecases.CalendarUseCase

class CalendarViewModel(
    private val calendarUseCase: CalendarUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<CalendarState>(CalendarState.Loading)
    val state: State<CalendarState> = _state

    private val year = 2025
    private val month = Month.December

    init {
        calendarUseCase.observe.onEach { calendar ->
            val money = getSalary() / calendar.size
            _state.value = CalendarState.Data(
                month = month,
                year = year,
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
        calendarUseCase.invoke(
            args = CalendarUseCase.Args(
                month = month,
                year = year,
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

    fun prevMonth(month: Month, year: Int): Pair<Month, Int> {
        val monthIndex = Month.entries.indexOf(month)
        val (newMonth, newYear) = if (monthIndex == 0) {
            Month.entries[11] to year - 1
        } else {
            Month.entries[monthIndex - 1] to year
        }
        calendarUseCase.invoke(
            CalendarUseCase.Args(
                month = newMonth,
                year = newYear,
            )
        )
        return newMonth to newYear
    }

    fun nextMonth(month: Month, year: Int): Pair<Month, Int> {
        val monthIndex = Month.entries.indexOf(month)
        val (newMonth, newYear) = if (monthIndex == 11) {
            Month.entries[0] to year + 1
        } else {
            Month.entries[monthIndex + 1] to year
        }
        calendarUseCase.invoke(
            CalendarUseCase.Args(
                month = newMonth,
                year = newYear,
            )
        )
        return newMonth to newYear
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