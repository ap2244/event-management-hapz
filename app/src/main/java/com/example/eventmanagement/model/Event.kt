package com.example.eventmanagement.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String = "",
    var dateTime: String = "",
    var location: String = "",
    var description: String = "",
    var participants: ArrayList<String> = arrayListOf(),
)