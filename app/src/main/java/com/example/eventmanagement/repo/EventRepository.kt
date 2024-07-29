package com.example.eventmanagement.repo

import androidx.lifecycle.LiveData
import com.example.eventmanagement.database.EventDao
import com.example.eventmanagement.model.Event
import kotlinx.coroutines.flow.Flow

class EventRepository(private val eventDao: EventDao) {

    fun getAllEvents(): LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun getEventById(eventId: Int): Event = eventDao.getEventById(eventId)

    fun getEventByIdLive(eventId: Int): LiveData<Event> = eventDao.getEventByIdLive(eventId)

    fun searchEvents(searchText: String): LiveData<List<Event>> = eventDao.searchEvents(searchText)

    suspend fun insert(event: Event) = eventDao.insert(event)

    suspend fun update(event: Event) = eventDao.update(event)

}