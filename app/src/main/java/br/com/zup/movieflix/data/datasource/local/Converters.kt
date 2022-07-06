package br.com.zup.movieflix.data.datasource.local

import androidx.room.TypeConverter
import br.com.zup.movieflix.domain.model.Director
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun converterFromDirector(value: String): Director? {
        return Gson().fromJson(value, Director::class.java)
    }

    @TypeConverter
    fun converterToJson(director: Director): String {
        return Gson().toJson(director)
    }

    @TypeConverter
    fun fromStringToListInt(value: String): List<Int> {
        val listType: Type = object : TypeToken<List<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<Int?>?): String? {
        return Gson().toJson(list)
    }
}