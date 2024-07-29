package com.example.eventmanagement.viewmodels

import android.app.Application
import android.system.Os.remove
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eventmanagement.database.AppDatabase
import com.example.eventmanagement.model.Event
import com.example.eventmanagement.repo.EventRepository
import kotlinx.coroutines.launch

class CreateEventViewModel(application: Application, private val eventId: Int?) : AndroidViewModel(application) {
    private val eventDao = AppDatabase.getDatabase(application).eventDao()
    private val repository: EventRepository = EventRepository(eventDao)

    val eventName = MutableLiveData<String>()
    val eventDateTime = MutableLiveData<String>()
    val eventLocation = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val titleBarName = MutableLiveData<String>()
    var _eventParticipants = MutableLiveData<ArrayList<String>>()

    init {
        if (eventId != 0) {
            titleBarName.value = "Update Event"
            viewModelScope.launch {
                val event = repository.getEventById(eventId!!)
                event.let {
                    eventName.value = it.name
                    eventDateTime.value = it.dateTime
                    eventLocation.value = it.location
                    eventDescription.value = it.description
                    _eventParticipants.value = it.participants
                }
            }
        } else {
            titleBarName.value = "Create Event"
            eventDateTime.value = "Select Date & Time"
        }
    }

    fun saveEvent(onEventSaved: () -> Unit) {
        viewModelScope.launch {
            val event = Event(
                id = eventId ?: 0,
                name = eventName.value ?: "",
                dateTime = eventDateTime.value ?: "",
                location = eventLocation.value ?: "",
                description = eventDescription.value ?: "",
                participants = _eventParticipants.value ?: arrayListOf()
            )

            if (eventId != 0) {
                repository.update(event)
            } else {
                repository.insert(event)
            }

            onEventSaved()
        }
    }

}


