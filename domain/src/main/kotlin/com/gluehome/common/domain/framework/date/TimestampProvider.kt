package com.gluehome.common.domain.framework.date

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class TimestampProvider {

    fun generateIsoFormattedTimestamp(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.format(date)
    }
}