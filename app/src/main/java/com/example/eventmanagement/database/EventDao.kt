package com.example.eventmanagement.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eventmanagement.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("SELECT * FROM event")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM event WHERE id = :eventId")
    suspend fun getEventById(eventId: Int): Event

    @Query("SELECT * FROM event WHERE id = :eventId")
    fun getEventByIdLive(eventId: Int): LiveData<Event>

    @Query("SELECT * FROM event WHERE name LIKE :searchQuery")
    fun searchEvents(searchQuery: String): LiveData<List<Event>>
}