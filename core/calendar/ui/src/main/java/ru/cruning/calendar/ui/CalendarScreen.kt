package ru.cruning.calendar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cruning.calendar.ui.Month.*
import ru.cruning.calendar.ui.Week.Monday


private val list = (1..30).map {
    DayUi(
        dayOfTheWeek = Monday,
        dayOfMonth = it,
        isToday = it == 11,
        isSelected = it == 9
    )
}

@Composable
fun CalendarScreen(modifier: Modifier) {
    //todo из viewModel
    Calendar(
        February,
        list
    )
}

@Composable
fun MonthTitle(month: Month) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
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
            text = month.name + " " + "2024"
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
    list: List<DayUi>,
) {
    Column {
        MonthTitle(month)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
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
                        color = Color.Black,
                        textAlign = TextAlign.Center,

                        )
                }
            }
            items(list) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
//                        .height(44.dp)
                ) {
                    Day(it)
                }
            }
        }
    }
}

@Composable
fun Day(day: DayUi) {
    Box(
        modifier = Modifier
            .height(44.dp)
            .width(44.dp)
            .background(
                color = if (day.isSelected) Color.Blue else Color.White,
                shape = CircleShape,
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = day.dayOfMonth.toString(),
                color = if (day.isSelected) Color.White else Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
            if (day.isToday) {
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(
                            color = Color.Blue,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview(device = "id:pixel_7", showSystemUi = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(Modifier)
}


@Preview(widthDp = 412, backgroundColor = 0xFFFFFFFF)
@Composable
fun MonthTitlePreview() {
    MonthTitle(month = January)
}

@Preview
@Composable
fun MonthPreview() {
    Calendar(
        month = January,
        list = list
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
            isSelected = false,
        )
    )
}

@Preview(widthDp = 49, heightDp = 44)
@Composable
fun TodayPreview() {
    Day(
        DayUi(
            dayOfTheWeek = Monday,
            dayOfMonth = 20,
            isToday = true,
            isSelected = false,
        )
    )
}

@Preview(widthDp = 49, heightDp = 44)
@Composable
fun SelectedDayPreview() {
    Day(
        DayUi(
            dayOfTheWeek = Monday,
            dayOfMonth = 20,
            isToday = true,
            isSelected = true
        )
    )
}

data class DayUi(
    val dayOfTheWeek: Week,
    val dayOfMonth: Int,
    val isToday: Boolean,
    val isSelected: Boolean,
)


enum class Week(
    val abbreviatedName: String
) {
    Monday("Mon"),
    Tuesday("Tue"),
    Wednesday("Wed"),
    Thursday("Thu"),
    Friday("Fri"),
    Saturday("Sat"),
    Sunday("Sun"),
}

enum class Month {
    January,
    February,
}