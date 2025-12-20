package ru.cruning.calendar.domain.usecases

import kotlinx.coroutines.flow.flow
import ru.cruning.calendar.domain.mappers.DayMapper
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.usecases.CalendarUseCase.Args

class CalendarUseCase(
    private val dayMapper: DayMapper
) : FlowUseCase<Args, List<Day>>() {

    data class Args(
        val month: Month,
        val year: Int,
    )

    override fun createFlow(args: Args) = flow {
        val countDays = getCountDays(args.year, args.month.ordinal + 1)
        (1..countDays).map { day ->
            dayMapper.mapFrom(
                year = args.year,
                month = args.month,
                digit = day,
            )
        }.let { emit(it) }
    }

    private fun getCountDays(year: Int, month: Int) = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        2 -> if (year % 4 == 0) 29 else 28
        else -> 30
    }
}