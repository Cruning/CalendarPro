package ru.cruning.calendar.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cruning.calendar.data.repository.CalendarRepository
import ru.cruning.calendar.data.repository.CalendarRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsCalendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository
}