package ru.cruning.calendar.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.cruning.calendar.data.CalendarApi
import ru.cruning.calendar.domain.repository.CalendarRepository

internal class CalendarRepositoryImpl(
    private val api: CalendarApi,
) : CalendarRepository {
    override suspend fun updateCalendar() = withContext(Dispatchers.IO) {
        api.getCalendar("ru", "2025")
        //insert
        true
    }
}