package br.com.git.domain.converters

import android.arch.persistence.room.TypeConverter
import br.com.git.domain.entity.owner
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson



/**
 * Created by Felipe on 26/01/2018.
 */
class OwnerTypeConverters {
    @TypeConverter
    fun stringToOwner(json: String): owner {
        val gson = Gson()
        val type = object : TypeToken<owner>() {

        }.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun ownerToString(item: owner): String {
        val gson = Gson()
        val type = object : TypeToken<owner>() {

        }.type
        return gson.toJson(item, type)
    }
}