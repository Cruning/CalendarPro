package ru.cruning.calendar.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.cruning.calendar.data.models.CalendarDTO

internal interface CalendarApi {
    @GET("data/{region}/{year}/calendar.json")
    suspend fun getCalendar(
        @Path("region") region: String,
        @Path("year") year: String,
    ): CalendarDTO
}