package com.example.eventmanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.eventmanagement.database.AppDatabase
import com.example.eventmanagement.model.Event
import com.example.eventmanagement.repo.EventRepository


class EventsListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventRepository

    var searchQuery = MutableLiveData<String>()
    var events: LiveData<List<Event>>

    init {
        val eventDao = AppDatabase.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
        events = searchQuery.switchMap { query ->
            if (query == null || query.length == 0) {
                repository.getAllEvents()
            } else {
                repository.searchEvents("%${query}%")
            }
        }
        search("")
    }

    fun search(searchText: String) {
        searchQuery.value = searchText
    }

}
