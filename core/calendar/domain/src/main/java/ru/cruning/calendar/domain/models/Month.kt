package ru.cruning.calendar.domain.models

import java.util.Calendar

enum class Month(val monthJavaUtil: Int) {
    January(Calendar.JANUARY),
    February(Calendar.FEBRUARY),
    March(Calendar.MARCH),
    April(Calendar.APRIL),
    May(Calendar.MAY),
    June(Calendar.JUNE),
    July(Calendar.JULY),
    August(Calendar.AUGUST),
    September(Calendar.SEPTEMBER),
    October(Calendar.OCTOBER),
    November(Calendar.NOVEMBER),
    December(Calendar.DECEMBER);
}