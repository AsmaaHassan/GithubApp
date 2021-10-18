package com.example.githubapp.utils

import java.text.SimpleDateFormat
import java.util.*
/**
 * Created by Asmaa Hassan
 */
object DateUtil {
    fun getDateMonthBefore(): String{
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val date: Date = calendar.getTime()
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateOutput: String = format.format(date)
        return dateOutput
    }
}