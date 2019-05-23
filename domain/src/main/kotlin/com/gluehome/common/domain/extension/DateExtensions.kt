package com.gluehome.common.domain.extension

import java.util.Calendar
import java.util.Date

fun Date.isWeekend(): Boolean {
    val cal = Calendar.getInstance().apply {
        time = this@isWeekend
    }

    return (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
}

fun Date.isFriday(): Boolean {
    val cal = Calendar.getInstance().apply {
        time = this@isFriday
    }

    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}

fun Date.addDays(days: Int): Date {
    val cal = Calendar.getInstance().apply {
        time = this@addDays
    }

    cal.add(Calendar.DATE, days)
    return cal.time
}

fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun Long.toDate(): Date = Date(this)
fun Date.toLong(): Long = this.time

fun Long.toDateWithHour(hour: Int): Date {
    val cal = this.toDate().toCalendar()
    cal.set(Calendar.HOUR_OF_DAY, hour)
    return cal.time
}

fun Long?.or(defaultValue: Long = 0): Long = this ?: defaultValue
