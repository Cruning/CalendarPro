package ru.cruning.calendar.domain.mappers

import kotlinx.datetime.LocalDate
import ru.cruning.calendar.domain.models.Day
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Week

//
//todo не самое универсальное решение, либо доработать либо отказаться от него
//import kotlinx.datetime.LocalDate
//import ru.cruning.calendar.domain.mappers.DayMapperImpl.From
//import ru.cruning.calendar.domain.models.Day
//import ru.cruning.calendar.domain.models.Month
//import ru.cruning.calendar.domain.models.Week
//
//interface Mapper<From, To> {
//    fun mapTo(from: From): To
//    fun mapFrom(from: To): From
//}
//
//interface DayMapper : Mapper<From, Day>
//
//class DayMapperImpl(
//) : DayMapper {
//
//    data class From(
//        val digit: Int,
//        val year: Int,
//        val month: Month,
//    )
//
//    override fun mapTo(from: From): Day {
//        return Day(
//            dayOfTheWeek = LocalDate(
//                year = from.year,
//                month = from.month.ordinal + 1,
//                day = from.digit,
//            ).dayOfWeek.ordinal.let(Week.entries::get),
//            dayOfMonth = from.digit,
//            isFree = false,
//        )
//    }
//
//    override fun mapFrom(from: Day) = From(
//        digit = from.dayOfMonth,
//        year = -1,
//        month = Month.May,
//    )
//}

class DayMapper() {
    fun mapFrom(
        year: Int,
        month: Month,
        digit: Int,
    ) = Day(
        dayOfTheWeek = LocalDate(
            year = year,
            month = month.ordinal + 1,
            day = digit,
        ).dayOfWeek.ordinal.let(Week.entries::get),
        dayOfMonth = digit,
        isFree = false,
    )
}