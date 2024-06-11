package com.example.flowerdelivery.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(value: List<Int>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<Int> {
        if (value.isEmpty()) return listOf()
        return value.split(",").map { it.toInt() }
    }
}