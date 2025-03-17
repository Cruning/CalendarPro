package ru.cruning.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cruning.core.data.repository.CalendarRepository
import ru.cruning.core.data.repository.CalendarRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsCalendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository
}