package com.gluehome.common.domain.framework.date

import java.text.SimpleDateFormat
import java.util.*

class TimestampProvider {

    fun generateIsoFormattedTimestamp(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK)
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.format(date)
    }
}