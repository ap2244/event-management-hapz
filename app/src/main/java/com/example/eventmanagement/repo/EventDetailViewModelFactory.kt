package com.example.eventmanagement.repo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagement.viewmodels.EventDetailViewModel

class EventDetailViewModelFactory(
    private val application: Application,
    private val eventId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
            return EventDetailViewModel(application, eventId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
