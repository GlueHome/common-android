package com.gluehome.common.domain.framework

import com.gluehome.common.domain.extension.toDate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateMapper {

    // Thursday, August 14th
    fun formatDate(date: Date, format: String): String {
        return try {
            SimpleDateFormat(format, Locale.getDefault()).format(date)
        } catch (exception: Exception) {
            "Unable to parse date"
        }
    }

    fun dateToCardHeaderTitle(date: Date): String {
        val cal = Calendar.getInstance()
        cal.time = date
        val day = cal.get(Calendar.DATE)

        return when (day) {
            1, 21, 31 -> customFormat("st").format(date)
            2, 22 -> customFormat("nd").format(date)
            3, 23 -> customFormat("rd").format(date)
            else -> customFormat("th").format(date)
        }
    }

    private fun customFormat(ordinal: String): SimpleDateFormat {
        return SimpleDateFormat("EEEE, MMMM d'$ordinal'", Locale.getDefault())
    }

    fun toDayMonthYearTimestamp(stringDate: String): Date {
        return transformStringDateInIntoTimestamp(stringDate, "dd/MM/yy").toDate()
    }

    private fun transformStringDateInIntoTimestamp(stringDate: String, dateFormat: String): Long {
        val date: Date
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        date = try {
            formatter.parse(stringDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
        return date.time
    }
}
