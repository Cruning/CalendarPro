package ru.cruning.core.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.cruning.core.data.CalendarApi
import ru.cruning.core.data.models.CalendarDTO
import javax.inject.Inject

internal class CalendarRepositoryImpl @Inject constructor(
    private val api: CalendarApi,
) : CalendarRepository {
    override suspend fun updateCalendar() = withContext(Dispatchers.IO) {
        api.getCalendar("ru", "2025")
    }
}

interface CalendarRepository {
    suspend fun updateCalendar(): CalendarDTO
}