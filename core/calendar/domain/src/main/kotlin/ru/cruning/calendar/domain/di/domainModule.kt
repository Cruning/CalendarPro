package ru.cruning.calendar.domain.di

import org.koin.dsl.module
import ru.cruning.calendar.domain.mappers.DayMapper
import ru.cruning.calendar.domain.usecases.CalendarUseCase

val useCaseModule = module {
    factory<CalendarUseCase> { CalendarUseCase(get()) }
}

val mapperModule = module {
    factory { DayMapper() }
}