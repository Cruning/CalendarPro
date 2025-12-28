package ru.cruning.calendar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.usecases.CalendarUseCase

class CalendarViewModel(
    private val calendarUseCase: CalendarUseCase,
) : ViewModel(), ContainerHost<CalendarState, CalendarSideEffect>, CalendarActions {

    override val container: Container<CalendarState, CalendarSideEffect> = container(
        CalendarState(
            month = Month.December,
            year = 2025,
            days = emptyList(),
            isLoading = true,
        )
    )

    init {
        calendarUseCase.observe.onEach { calendar ->
            val money = getSalary() / calendar.size
            intent {
                reduce {
                    val emptyDays = List(calendar.first().dayOfTheWeek.ordinal) { null }
                    CalendarState(
                        month = state.month,
                        year = state.year,
                        days = emptyDays + calendar.map {
                            DayUi(
                                dayOfTheWeek = it.dayOfTheWeek,
                                dayOfMonth = it.dayOfMonth,
                                money = money,
                                isToday = false,
                                isSelected = false,
                                isFree = it.isFree,
                            )
                        },
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
        calendarUseCase(CalendarUseCase.Args(Month.December, 2025))
    }

    override fun intentPrevMonth() {
        intent {
            val (month, year) = prevMonth(state.month, state.year)
            reduce {
                CalendarState(
                    month = month,
                    year = year,
                    days = emptyList(),
                    isLoading = true,
                )
            }
            calendarUseCase(CalendarUseCase.Args(month, year))
        }
    }

    override fun intentNextMonth() {
        intent {
            val (month, year) = nextMonth(state.month, state.year)
            reduce {
                CalendarState(
                    month = month,
                    year = year,
                    days = emptyList(),
                    isLoading = true,
                )
            }
            calendarUseCase(CalendarUseCase.Args(month, year))
        }
    }

    override fun intentSelectDay(dayUi: DayUi) {
        intent {
            reduce {
                state.copy(days = state.days.map { day ->
                    if (day == dayUi) {
                        day.copy(isSelected = !dayUi.isSelected)
                    } else {
                        day?.copy(isSelected = false)
                    }
                })
            }
        }
    }

    private fun prevMonth(month: Month, year: Int): Pair<Month, Int> {
        val monthIndex = Month.entries.indexOf(month)
        val (newMonth, newYear) = if (monthIndex == 0) {
            Month.entries[11] to year - 1
        } else {
            Month.entries[monthIndex - 1] to year
        }
        return newMonth to newYear
    }

    private fun nextMonth(month: Month, year: Int): Pair<Month, Int> {
        val monthIndex = Month.entries.indexOf(month)
        val (newMonth, newYear) = if (monthIndex == 11) {
            Month.entries[0] to year + 1
        } else {
            Month.entries[monthIndex + 1] to year
        }
        return newMonth to newYear
    }

    //todo вынести UseCase
    private fun getSalary(): Float {
        return 100000f
    }

}

data class CalendarState(
    val month: Month,
    val year: Int,
    val days: List<DayUi?>,
    val isLoading: Boolean,
)

interface CalendarSideEffect {}
interface CalendarActions {
    fun intentPrevMonth()
    fun intentNextMonth()
    fun intentSelectDay(dayUi: DayUi)
}