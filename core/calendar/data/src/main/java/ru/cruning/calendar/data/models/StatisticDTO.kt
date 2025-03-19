package ru.cruning.calendar.data.models

data class StatisticDTO(
    val holidays: Int,
    val hours24: Double,
    val hours36: Double,
    val hours40: Int,
    val workdays: Int
)