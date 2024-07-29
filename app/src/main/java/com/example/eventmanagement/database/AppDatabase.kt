package com.example.eventmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.eventmanagement.model.Event

class Converters {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        return ArrayList(value.split(",").map { it.trim() })
    }

    @TypeConverter
    fun listToString(list: ArrayList<String>): String {
        return list.joinToString(",")
    }
}

@Database(entities = [Event::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "event_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}