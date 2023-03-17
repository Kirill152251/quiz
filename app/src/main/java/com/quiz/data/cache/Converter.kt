package com.quiz.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quiz.domain.models.Question

@ProvidedTypeConverter
class Converter {

    @TypeConverter
    fun fromString(string: String): List<String> {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromQuestionJson(string: String): List<Question> {
        val type = object : TypeToken<ArrayList<Question>>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun toQuestionJson(list: List<Question>): String {
        return Gson().toJson(list)
    }
}