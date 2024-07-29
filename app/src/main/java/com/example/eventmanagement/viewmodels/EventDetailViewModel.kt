package com.example.eventmanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.eventmanagement.database.AppDatabase
import com.example.eventmanagement.model.Event
import com.example.eventmanagement.repo.EventRepository

class EventDetailViewModel(application: Application, eventId: Int) : AndroidViewModel(application) {
    private val eventDao = AppDatabase.getDatabase(application).eventDao()
    private val repository: EventRepository = EventRepository(eventDao)
    val titleBarName = MutableLiveData<String>()

    val event: LiveData<Event>

    init {
        event = repository.getEventByIdLive(eventId)
        titleBarName.value = "Event Details"
    }


}
