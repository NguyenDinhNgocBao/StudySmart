package com.example.studysmart.data.local

import androidx.room.TypeConverter

class ColorListConverter {

    @TypeConverter
    fun fromClorList(colorList: List<Int>):String{
        return colorList.joinToString ( "," ){it.toString()}
    }

    @TypeConverter
    fun toColorList(colorListString: String): List<Int>{
        return colorListString.split(",").map { it.toInt() }
    }
}