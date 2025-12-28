package ru.cruning.calendar.data.di

import org.koin.dsl.module
import ru.cruning.calendar.data.repository.CalendarRepository
import ru.cruning.calendar.data.repository.CalendarRepositoryImpl

val repositoryModule = module {
    single<CalendarRepository> { CalendarRepositoryImpl(get()) }
}