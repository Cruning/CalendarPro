package ru.cruning.calendar.domain.usecases

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import ru.cruning.calendar.domain.mappers.DayMapper
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.usecases.CalendarUseCase.Args

class CalendarUseCaseTest {
    private val dayMapper = DayMapper()

    @Test
    fun calendarUseCaseDecember2025Test() = runTest {
        val useCase = CalendarUseCase(dayMapper)
        useCase.invoke(
            Args(
                month = Month.December,
                year = 2025,
            )
        )
        val actual = useCase.observe.first()
        assertEquals(December2025, actual)
    }

    @Test
    fun calendarUseCaseFebruaryLeap2024Test() = runTest {
        val useCase = CalendarUseCase(dayMapper)
        useCase.invoke(
            Args(
                month = Month.February,
                year = 2024,
            )
        )
        val actual = useCase.observe.first()
        assertEquals(FebruaryLeap2024, actual)
    }

    @Test
    fun calendarUseCaseFebruaryNotLeap2025Test() = runTest {
        val useCase = CalendarUseCase(dayMapper)
        useCase.invoke(
            Args(
                month = Month.February,
                year = 2025,
            )
        )
        val actual = useCase.observe.first()
        assertEquals(FebruaryNotLeap2025, actual)
    }

    private val December2025 = listOf(
        Day(
            dayOfMonth = 1,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 2,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 3,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 4,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 5,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 6,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 7,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 8,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 9,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 10,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 11,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 12,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 13,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 14,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 15,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 16,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 17,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 18,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 19,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 20,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 21,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 22,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 23,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 24,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 25,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 26,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 27,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 28,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 29,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 30,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 31,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
    )
    private val FebruaryLeap2024 = listOf(
        Day(
            dayOfMonth = 1,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 2,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 3,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 4,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 5,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 6,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 7,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 8,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 9,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 10,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 11,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 12,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 13,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 14,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 15,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 16,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 17,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 18,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 19,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 20,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 21,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 22,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 23,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 24,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 25,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 26,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 27,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 28,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 29,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
    )
    private val FebruaryNotLeap2025 = listOf(
        Day(
            dayOfMonth = 1,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 2,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 3,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 4,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 5,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 6,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 7,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 8,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 9,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 10,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 11,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 12,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 13,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 14,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 15,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 16,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 17,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 18,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 19,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 20,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 21,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 22,
            dayOfTheWeek = Week.Saturday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 23,
            dayOfTheWeek = Week.Sunday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 24,
            dayOfTheWeek = Week.Monday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 25,
            dayOfTheWeek = Week.Tuesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 26,
            dayOfTheWeek = Week.Wednesday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 27,
            dayOfTheWeek = Week.Thursday,
            isFree = false,
        ),
        Day(
            dayOfMonth = 28,
            dayOfTheWeek = Week.Friday,
            isFree = false,
        ),
    )
}