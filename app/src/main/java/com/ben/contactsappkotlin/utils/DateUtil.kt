package com.ben.contactsappkotlin.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    @JvmStatic
    fun convertDate(time: Long):String {
        val formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return formatter.format(calendar.time)
    }
}