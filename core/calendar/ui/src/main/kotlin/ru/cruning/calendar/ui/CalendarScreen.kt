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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import ru.cruning.core.designsystem.compose.theme.Typography
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import org.orbitmvi.orbit.compose.collectAsState
import ru.cruning.calendar.domain.models.Month
import ru.cruning.calendar.domain.models.Month.January
import ru.cruning.calendar.domain.models.Week
import ru.cruning.calendar.domain.models.Week.Monday
import ru.cruning.calendar.domain.models.Week.Thursday


@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = koinInject<CalendarViewModel>(),
) {
    val state by viewModel.collectAsState()
    println("tag1: $state")
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Calendar(
            month = state.month,
            year = state.year,
            list = state.days,
            viewModel::intentSelectDay,
            viewModel::intentNextMonth,
            viewModel::intentPrevMonth,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
    }
}

@Composable
fun MonthTitle(
    month: Month,
    year: Int,
    nextMonth: () -> Unit,
    prevMonth: () -> Unit,
) {
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
            onClick = {
                prevMonth()
            },
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
            onClick = {
                nextMonth()
            },
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
    list: List<DayUi?>,
    clickDay: (DayUi) -> Unit,
    nextMonthClickDay: () -> Unit,
    prevMonthClickDay: () -> Unit,
) {
    Card {
        MonthTitle(
            month,
            year,
            nextMonthClickDay,
            prevMonthClickDay,
        )
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
            items(list) { day ->
                Box(contentAlignment = Alignment.Center) {
                    day?.let { Day(day, clickDay) }
                }
            }
        }
        Money()
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
            color = when {
                day.isSelected -> White
                day.isFree -> Red
                else -> Black
//                White.takeIf { day.isSelected } ?: Black,
            },
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
        )
    }
//    todo точка должна быть снизу, а не в центре
//    if (day.isToday) {
//        Box(
//            modifier = Modifier
//                .size(3.dp)
//                .background(
//                    color = Blue,
//                    shape = CircleShape
//                )
//        )
//    }
}

@Composable
fun Money() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "21 Декабря",
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 32.dp,
            ),
            style = Typography.bodyMedium,
        )
        Text(
            text = "5900 рублей",
            style = Typography.bodyLarge,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            ),
        )
    }
}

@Preview(widthDp = 412)
@Composable
fun MoneyPreview() {
    Money()
}

@Preview(widthDp = 412, backgroundColor = 0xFFFFFFFF)
@Composable
fun MonthTitlePreview() {
    MonthTitle(
        month = January,
        2025,
        {},
        {},
    )
}

@Preview(widthDp = 412)
@Composable
fun MonthPreview() {
    Calendar(
        month = January,
        year = 2025,
        list = listOf(null, null, null) + (1..30).map {
            DayUi(
                dayOfTheWeek = Thursday,
                dayOfMonth = it,
                isToday = it == 11,
                money = 2000f,
                isSelected = it == 10,
                isFree = it == 3
                        || it == 4
                        || it == 10
                        || it == 11
                        || it == 17
                        || it == 18
                        || it == 24
                        || it == 25,
            )
        },
        {},
        {},
        {},
    )
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
            isFree = false,
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
            isFree = false,
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
            isSelected = true,
            isFree = false,
        ),
    ) {}
}

data class DayUi(
    val dayOfTheWeek: Week,
    val dayOfMonth: Int,
    val money: Float,
    val isToday: Boolean,
    val isSelected: Boolean,
    val isFree: Boolean,
)