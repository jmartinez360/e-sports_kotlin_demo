package com.example.lolresults.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "E, d MMM yyyy HH:mm"

    fun getDateFormatted(timestamp: Long): String {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return simpleDateFormat.format(Date(timestamp))
    }
}