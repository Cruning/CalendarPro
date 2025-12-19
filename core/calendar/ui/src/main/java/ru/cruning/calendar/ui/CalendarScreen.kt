package ru.cruning.calendar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Month.January
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.models.Week.Monday
import ru.cruning.calendar.domain.models.Week.Thursday


@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel<CalendarViewModel>(),
) {
    val rem by remember { viewModel.state }
    when (val state = rem) {
        is CalendarState.Data -> {
            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Calendar(
                    month = state.month,
                    year = state.year,
                    list = state.list,
                    viewModel::selectDay
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
        }

        CalendarState.Error -> {
            Text("Ошибка")
        }

        CalendarState.Loading -> {
            Text("Загрузка")
        }
    }
}

@Composable
fun MonthTitle(month: Month, year: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp,
            )
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        ) {
            Image(
                imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                contentDescription = null,
            )
        }
        Text(
            text = "${month.name} $year"
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        ) {
            Image(
                imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Calendar(
    month: Month,
    year: Int,
    list: List<DayUi>,
    clickDay: (DayUi) -> Unit,
) {
    Column {
        MonthTitle(month, year)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = 16.dp),
        ) {
            items(
                items = Week.entries
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(42.dp)
                ) {
                    Text(
                        text = it.abbreviatedName,
                        fontSize = 12.sp,
                        color = Black,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            items(listOf(1..Week.entries.indexOf(list.first().dayOfTheWeek))) {
                Box { }
            }
            items(list) {
                Box(contentAlignment = Alignment.Center) {
                    Day(it, clickDay)
                }
            }
        }
    }
}

@Composable
fun Day(day: DayUi, click: (DayUi) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(44.dp)
            .width(44.dp)
            .background(
                color = Blue.takeIf { day.isSelected } ?: White,
                shape = CircleShape,
            )
            .clickable { click.invoke(day) }
    ) {
        Text(
            text = day.dayOfMonth.toString(),
            color = White.takeIf { day.isSelected } ?: Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
        )
//        todo скорее всего будет под колендарем в отдельном блоке
//        Text(
//            text = day.money.toString(),
//            color = when {
//                day.dayOfMonth == 0 -> Color.Transparent
//                day.isSelected -> Color.White
//                else -> Color.Black
//            },
//            fontSize = 14.sp,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .wrapContentHeight()
//                .weight(1f)
//        )
    }
    if (day.isToday) {
        Box(
            modifier = Modifier
                .size(3.dp)
                .background(
                    color = Blue,
                    shape = CircleShape
                )
        )
    }
}

@Preview(device = "id:pixel_7", showSystemUi = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}

@Preview(widthDp = 412, backgroundColor = 0xFFFFFFFF)
@Composable
fun MonthTitlePreview() {
    MonthTitle(month = January, 2025)
}

@Preview(widthDp = 412)
@Composable
fun MonthPreview() {
    Calendar(
        month = January,
        year = 2025,
        list = (1..30).map {
            DayUi(
                dayOfTheWeek = Thursday,
                dayOfMonth = it,
                isToday = it == 11,
                money = 2000f,
                isSelected = it == 9
            )
        },
    ) {}
}

@Preview(widthDp = 49, heightDp = 44)
@Composable
fun DayPreview() {
    Day(
        DayUi(
            dayOfTheWeek = Monday,
            dayOfMonth = 1,
            isToday = false,
            money = 2000f,
            isSelected = false,
        ),
    ) {}
}

@Preview(widthDp = 49, heightDp = 44)
@Composable
fun TodayPreview() {
    Day(
        DayUi(
            dayOfTheWeek = Monday,
            dayOfMonth = 20,
            isToday = true,
            money = 2000f,
            isSelected = false,
        ),
    ) {}
}

@Preview(widthDp = 49, heightDp = 44)
@Composable
fun SelectedDayPreview() {
    Day(
        DayUi(
            dayOfTheWeek = Monday,
            dayOfMonth = 20,
            isToday = true,
            money = 2000f,
            isSelected = true
        ),
    ) {}
}

data class DayUi(
    val dayOfTheWeek: Week,
    val dayOfMonth: Int,
    val money: Float,
    val isToday: Boolean,
    val isSelected: Boolean,
)