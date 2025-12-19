package ru.cruning.calendar.domain.mappers

import kotlinx.datetime.LocalDate
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Week

interface Mapper<From, To> {
    fun mapTo(from: From): To
    fun mapFrom(from: To): From
}

interface DayMapper : Mapper<Int, Day>

class DayMapperImpl(
    private val year: Int,
    private val month: Month
) : DayMapper {
    override fun mapTo(from: Int) = Day(
        dayOfTheWeek = LocalDate(
            year = year,
            month = month.ordinal + 1,
            day = from
        ).dayOfWeek.ordinal.let(Week.entries::get),
        dayOfMonth = from,
        isFree = false,
    )

    override fun mapFrom(from: Day) = from.dayOfMonth
}
