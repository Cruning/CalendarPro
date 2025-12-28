package ru.cruning.calendar.ui

import org.koin.dsl.module

val viewModelModel = module {
    factory { CalendarViewModel(get()) }
}