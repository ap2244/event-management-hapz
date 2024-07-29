package com.example.eventmanagement.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagement.R
import com.example.eventmanagement.databinding.ActivityEventsListBinding
import com.example.eventmanagement.viewmodels.EventsListViewModel
import com.example.eventmanagement.views.adapters.EventListAdapter

class EventsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsListBinding
    private lateinit var viewModel: EventsListViewModel

    //    private lateinit var addEventLauncher: ActivityResultLauncher<Intent>
    private lateinit var eventAdapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_events_list)
        viewModel = ViewModelProvider(this).get(EventsListViewModel::class.java)

        eventAdapter = EventListAdapter(mutableListOf()) { event ->
            val intent = Intent(this, EventDetailActivity::class.java).apply {
                putExtra("EVENT_ID", event.id)
            }
            startActivity(intent)
        }

        binding.eventsRecyclerView.adapter = eventAdapter
        viewModel.events.observe(this, Observer { value ->
            eventAdapter.addAll(value)
        })

        binding.addEvent.setOnClickListener {
            val intent = Intent(this, CreateEventActivity::class.java)
            startActivity(intent)
        }

        binding.etSearch.doOnTextChanged { text, start, before, count ->
//            if (text!!.trim().toString().isEmpty()) {
//                viewModel.getAllEvents()
//            } else {
            viewModel.search(text!!.trim().toString())
//            }
        }

//        // Initialize ActivityResultLauncher
//        addEventLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
////                viewModel.refreshEvents()
//            }
//        }
    }
}

