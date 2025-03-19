package ru.cruning.calendar.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cruning.domain.TestFlowUsecase
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val flowUseCase: TestFlowUsecase,
) : ViewModel() {

    private val _state = mutableStateOf<CalendarState>(CalendarState.Loading)
    val state: State<CalendarState> = _state

    init {
        viewModelScope.launch {
            flowUseCase.observe.collect { countDay ->
                val money = getSalary() / countDay
                val firstDayIndex = 5
                _state.value = CalendarState.Data(
                    month = Month.March,
                    year = 2025,
                    list = (1..countDay + firstDayIndex).map { index ->
                        if (index < firstDayIndex) {
                            DayUi(
                                dayOfTheWeek = Week.entries[index],
                                dayOfMonth = 0,
                                money = money / countDay,
                                isToday = false,
                                isSelected = false,
                            )
                        } else {
                            val dayOfTheWeek: Week = Week.entries[index / Week.entries.size]
                            DayUi(
                                dayOfTheWeek = dayOfTheWeek,
                                dayOfMonth = index - firstDayIndex,
                                money = money / countDay,
                                isToday = index == 18,
                                isSelected = false,
                            )
                        }
                    }
                )
            }
        }
        flowUseCase(
            args = TestFlowUsecase.Args(
                qwer = "2",
                asdf = true,
            )
        )
    }

    //todo создать intents для этого экрана
    fun selectDay(day: DayUi) {
        when (val qwer = state.value) {
            is CalendarState.Data -> {
                _state.value = qwer.copy(
                    list = qwer.list.map {
                        if (day == it) {
                            day.copy(
                                isSelected = !it.isSelected
                            )
                        } else {
                            it.copy(
                                isSelected = false
                            )
                        }
                    }
                )
            }

            CalendarState.Error -> {}
            CalendarState.Loading -> {}
        }
    }

    //todo вынести UseCase
    private fun getSalary(): Float {
        return 100000f
    }

}

sealed interface CalendarState {
    data object Loading : CalendarState

    data class Data(
        val month: Month,
        val year: Int,
        val list: List<DayUi>,
    ) : CalendarState

    data object Error : CalendarState
}