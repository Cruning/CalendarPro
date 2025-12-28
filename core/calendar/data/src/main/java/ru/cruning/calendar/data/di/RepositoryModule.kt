package ru.cruning.calendar.data.di

import org.koin.dsl.module
import ru.cruning.calendar.data.repository.CalendarRepositoryImpl
import ru.cruning.calendar.domain.repository.CalendarRepository

val repositoryModule = module {
    single<CalendarRepository> { CalendarRepositoryImpl(get()) }
}