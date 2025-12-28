package ru.cruning.calendarpro

import android.app.Application
import org.koin.core.context.startKoin
import ru.cruning.calendar.data.di.repositoryModule
import ru.cruning.calendar.data.di.restModule
import ru.cruning.calendar.domain.di.mapperModule
import ru.cruning.calendar.domain.di.useCaseModule
import ru.cruning.calendar.ui.viewModelModel

class CalendarProApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                repositoryModule,
                restModule,
                useCaseModule,
                mapperModule,
                viewModelModel,
            )
        }
    }
}