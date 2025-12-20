package ru.cruning.calendar.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cruning.calendar.data.CalendarApi

val restModule = module {
    //todo
//    single private const val CALENDAR_URL = "https://xmlcalendar.ru/"

    single<CalendarApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://xmlcalendar.ru/")
            .build()
            .create(CalendarApi::class.java)
    }
}