package ru.cruning.calendar.domain.repository

interface CalendarRepository {
    suspend fun updateCalendar(): Boolean
}