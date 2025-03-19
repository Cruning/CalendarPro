package ru.cruning.calendar.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cruning.calendar.data.CalendarApi

@Module
@InstallIn(SingletonComponent::class)
internal class RestModule {
    @Provides
    internal fun provideRetrofit(): CalendarApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CALENDAR_URL)
            .build()
            .create(CalendarApi::class.java)
    }

    companion object {
        private const val CALENDAR_URL = "https://xmlcalendar.ru/"
    }
}