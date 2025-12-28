package ru.cruning.calendar.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.orbitmvi.orbit.test.test
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month.December
import ru.cruning.calendar.domain.models.Month.February
import ru.cruning.calendar.domain.models.Month.January
import ru.cruning.calendar.domain.models.Month.November
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.usecases.CalendarUseCase

class CalendarScreenTest {
    private val viewModel = CalendarViewModel(CalendarUseCaseFake())

    @Test
    fun nextMonthDecember2025Test() = runTest {
        viewModel.test(
            testScope = this,
            initialState = CalendarState(
                month = December,
                year = 2025,
                days = emptyList(),
                isLoading = true,
            )
        ) {
            containerHost.intentNextMonth()
            expectState {
                copy(
                    month = January,
                    year = 2026,
                )
            }
        }
    }

    @Test
    fun nextMonthJanuary2025Test() = runTest {
        viewModel.test(
            testScope = this,
            initialState = CalendarState(
                month = January,
                year = 2025,
                days = emptyList(),
                isLoading = true,
            )
        ) {
            containerHost.intentNextMonth()
            expectState {
                copy(
                    month = February,
                    year = 2025,
                )
            }
        }
    }

    @Test
    fun prevMonthDecember2025Test() = runTest {
        viewModel.test(
            testScope = this,
            initialState = CalendarState(
                month = December,
                year = 2025,
                days = emptyList(),
                isLoading = true,
            )
        ) {
            containerHost.intentPrevMonth()
            expectState {
                copy(
                    month = November,
                    year = 2025,
                )
            }
        }
    }

    @Test
    fun prevMonthJanuary2025Test() = runTest {
        viewModel.test(
            testScope = this,
            initialState = CalendarState(
                month = January,
                year = 2025,
                days = emptyList(),
                isLoading = true,
            )
        ) {
            containerHost.intentPrevMonth()
            expectState {
                copy(
                    month = December,
                    year = 2024,
                )
            }
        }
    }

    @Test
    fun selectDayTest() = runTest {
        viewModel.test(
            this,
            CalendarState(
                month = December,
                year = 2025,
                days = nonSelectDays,
                isLoading = true,
            )
        ) {
            containerHost.intentSelectDay(
                DayUi(
                    dayOfTheWeek = Week.Monday,
                    dayOfMonth = 1,
                    money = 5000f,
                    isToday = false,
                    isSelected = false,
                )
            )
            expectState {
                copy(days = oneDaySelect)
            }
        }
    }

    private val nonSelectDays = listOf(
        DayUi(
            dayOfTheWeek = Week.Monday,
            dayOfMonth = 1,
            money = 5000f,
            isToday = false,
            isSelected = false,
        ),
        DayUi(
            dayOfTheWeek = Week.Tuesday,
            dayOfMonth = 2,
            money = 5000f,
            isToday = false,
            isSelected = false,
        ),
    )

    private val oneDaySelect = listOf(
        DayUi(
            dayOfTheWeek = Week.Monday,
            dayOfMonth = 1,
            money = 5000f,
            isToday = false,
            isSelected = true,
        ),
        DayUi(
            dayOfTheWeek = Week.Tuesday,
            dayOfMonth = 2,
            money = 5000f,
            isToday = false,
            isSelected = false,
        ),
    )
}


class CalendarUseCaseFake : CalendarUseCase() {
    override fun createFlow(args: Args): Flow<List<Day>> = flow { }
}