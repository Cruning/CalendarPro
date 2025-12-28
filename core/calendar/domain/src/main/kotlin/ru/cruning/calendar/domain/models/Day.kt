package ru.cruning.calendar.domain.models

data class Day(
    val dayOfTheWeek: Week,
    val dayOfMonth: Int,
    val isFree: Boolean,
)
