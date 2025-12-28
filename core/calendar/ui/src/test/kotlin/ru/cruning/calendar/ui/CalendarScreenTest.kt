package ru.cruning.calendar.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month.December
import ru.cruning.calendar.domain.models.Month.February
import ru.cruning.calendar.domain.models.Month.January
import ru.cruning.calendar.domain.models.Month.November
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.usecases.CalendarUseCase

/**
 * todo nextMonth и prevMonth должны быть private
 */

class CalendarScreenTest {
    private val viewModel = CalendarViewModel(CalendarUseCaseFake())

    @Test
    fun nextMonthDecember2025Test() {
        val (month, year) = viewModel.nextMonth(December, 2025)
        assertEquals(January, month)
        assertEquals(2026, year)
    }

    @Test
    fun nextMonthJanuary2025Test() {
        val (month, year) = viewModel.nextMonth(January, 2025)
        assertEquals(February, month)
        assertEquals(2025, year)
    }

    @Test
    fun prevMonthDecember2025Test() {
        val (month, year) = viewModel.prevMonth(December, 2025)
        assertEquals(November, month)
        assertEquals(2025, year)
    }

    @Test
    fun prevMonthJanuary2025Test() {
        val (month, year) = viewModel.prevMonth(January, 2025)
        assertEquals(December, month)
        assertEquals(2024, year)
    }

    @Test
    fun selectDayTest() {
        val list3 = viewModel.qwer(
            DayUi(
                dayOfTheWeek = Week.Monday,
                dayOfMonth = 1,
                money = 5000f,
                isToday = false,
                isSelected = false,
            ),
            list
        )
        assertEquals(list2, list3)
    }

    private val list = listOf<DayUi>(
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

    private val list2 = listOf<DayUi>(
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