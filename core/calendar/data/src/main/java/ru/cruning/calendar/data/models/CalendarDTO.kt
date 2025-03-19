package ru.cruning.calendar.data.models

data class CalendarDTO(
    val months: List<MonthDTO>,
    val statistic: StatisticDTO,
    val transitions: List<TransitionDTO>,
    val year: Int,
)