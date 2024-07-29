package com.example.eventmanagement

import android.app.Application
import androidx.room.Room
import com.example.eventmanagement.database.AppDatabase

class MyApp : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "event_database").build()
    }
}