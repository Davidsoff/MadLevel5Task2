package nl.soffware.madlevel5task2.database.converter

import androidx.room.TypeConverter
import nl.soffware.madlevel5task2.model.Platform

class PlatformConverters {
    @TypeConverter
    fun fromPlatform(value: Platform?): String {
        return value!!.name
    }
    @TypeConverter
    fun toPlatform(value: String): Platform {
        return enumValueOf(value)
    }
}