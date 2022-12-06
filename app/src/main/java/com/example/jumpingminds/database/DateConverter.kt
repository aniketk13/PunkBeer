package com.example.jumpingminds.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimeStamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long {
        return date.time
    }
}