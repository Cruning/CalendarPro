package ru.cruning.core.data.models

data class CalendarDTO(
    val months: List<MonthDTO>,
    val statistic: StatisticDTO,
    val transitions: List<TransitionDTO>,
    val year: Int,
)