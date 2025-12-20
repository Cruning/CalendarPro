package ru.cruning.calendar.domain.di

import org.koin.dsl.module
import ru.cruning.calendar.domain.mappers.DayMapper
import ru.cruning.calendar.domain.usecases.CalendarUseCase
import ru.cruning.calendar.domain.usecases.CalendarUseCaseImpl

val useCaseModule = module {
    factory<CalendarUseCase> { CalendarUseCaseImpl(get()) }
}

val mapperModule = module {
    factory { DayMapper() }
}