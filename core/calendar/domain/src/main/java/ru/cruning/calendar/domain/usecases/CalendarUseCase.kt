package ru.cruning.calendar.domain.usecases

import kotlinx.coroutines.flow.flow
import ru.cruning.calendar.domain.models.Calendar
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.usecases.CalendarUseCase.Args
import javax.inject.Inject

class CalendarUseCase @Inject constructor(
) : FlowUseCase<Args, Calendar>() {

    data class Args(
        val month: Month,
        val year: Int,
    )

    override fun createFlow(args: Args) = flow {
        val firstDayOfWeek = java.util.Calendar.getInstance().let {
            it.set(args.year + 1900, Month.entries.indexOf(args.month), 1)
            it.get(java.util.Calendar.DAY_OF_WEEK)
        }
        when (Month.entries.indexOf(args.month)) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            2 -> 28
            else -> 30
        }.let { size ->
            (1..size)
                .map {
                    Day(
                        dayOfTheWeek = Week.entries[it + firstDayOfWeek / Week.entries.size],
                        dayOfMonth = it,
                        isFree = false
                    )
                }
        }.let(::Calendar).let {
            emit(it)
        }
    }

}