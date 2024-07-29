package com.example.eventmanagement.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventmanagement.R
import com.example.eventmanagement.databinding.ActivityEventDetailBinding
import com.example.eventmanagement.repo.EventDetailViewModelFactory
import com.example.eventmanagement.viewmodels.EventDetailViewModel
import com.example.eventmanagement.views.adapters.EventDetailAdapter

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private lateinit var viewModel: EventDetailViewModel
    private lateinit var participantsAdapter: EventDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        val eventId = intent.getIntExtra("EVENT_ID", 0)
        viewModel = ViewModelProvider(this, EventDetailViewModelFactory(application, eventId)).get(EventDetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        participantsAdapter = EventDetailAdapter(arrayListOf())
        binding.rvParticipants.adapter = participantsAdapter
        binding.rvParticipants.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                LinearLayoutManager.VERTICAL
            )
        )

        viewModel.event.observe(this, Observer { event ->
            binding.event = event
            participantsAdapter.addAll(event.participants)
        })

        binding.editButton.setOnClickListener {
            val intent = Intent(this, CreateEventActivity::class.java).apply {
                putExtra("EVENT_ID", eventId)
            }
            startActivity(intent)
        }
    }

}
