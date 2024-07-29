package com.example.eventmanagement.views

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventmanagement.R
import com.example.eventmanagement.databinding.ActivityCreateEventBinding
import com.example.eventmanagement.repo.CreateEventViewModelFactory
import com.example.eventmanagement.viewmodels.CreateEventViewModel
import com.example.eventmanagement.views.adapters.ParticipantsAdapter
import java.util.Calendar


class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEventBinding
    private lateinit var viewModel: CreateEventViewModel
    private lateinit var participantsAdapter: ParticipantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event)

        var eventId = intent.getIntExtra("EVENT_ID", 0)
        viewModel = ViewModelProvider(this, CreateEventViewModelFactory(application, eventId)).get(CreateEventViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        participantsAdapter = ParticipantsAdapter(arrayListOf()) { participant ->
            val oldList = ArrayList(viewModel._eventParticipants.value.orEmpty())
            oldList.remove(participant)
            viewModel._eventParticipants.value = oldList
        }

        binding.rvParticipants.adapter = participantsAdapter
        binding.rvParticipants.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.addParticipantButton.setOnClickListener {
            showAddParticipantDialog()
        }

        binding.tvDateTime.setOnClickListener {
            datePicker()
        }

        viewModel._eventParticipants.observe(this, Observer { participants ->
            participantsAdapter.addAll(participants)
        })

        binding.saveButton.setOnClickListener {
            if (validateEventDetails()) {
                viewModel.saveEvent {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

    private fun showAddParticipantDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_participant, null)
        val participantEditText = dialogView.findViewById<EditText>(R.id.participantEditText)

        AlertDialog.Builder(this)
            .setTitle("Add Participant")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val participantName = participantEditText.text.toString().trim()
                if (participantName.isNotEmpty()) {
                    val oldList = ArrayList(viewModel._eventParticipants.value.orEmpty())
                    oldList.add(participantName)
                    viewModel._eventParticipants.value = oldList
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun validateEventDetails(): Boolean {
        var isValid = true
        with(binding) {
            with(this@CreateEventActivity.viewModel) {
                if (eventName.value.isNullOrEmpty()) {
                    etEventName.setError("Event name is required")
                    isValid = false
                }
                if (eventDateTime.value.isNullOrEmpty()) {
                    tvDateTime.setError("Event date and time are required")
                    isValid = false
                }
                if (eventLocation.value.isNullOrEmpty()) {
                    etLocation.setError("Event location is required")
                    isValid = false
                }
                if (eventDescription.value.isNullOrEmpty()) {
                    etDescription.setError("Event description is required")
                    isValid = false
                }
                if (_eventParticipants.value.isNullOrEmpty()) {
                    Toast.makeText(this@CreateEventActivity, "At least one participant is required", Toast.LENGTH_SHORT).show()
                    isValid = false
                }
            }
        }

        return isValid
    }

    private fun datePicker() {
        val c: Calendar = Calendar.getInstance()
        var mYear = c.get(Calendar.YEAR)
        var mMonth = c.get(Calendar.MONTH)
        var mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                viewModel.eventDateTime.value = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                tiemPicker()
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun tiemPicker() {
        val c = Calendar.getInstance()
        var mHour = c[Calendar.HOUR_OF_DAY]
        var mMinute = c[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                mHour = hourOfDay
                mMinute = minute
                viewModel.eventDateTime.value = viewModel.eventDateTime.value + " " + hourOfDay + ":" + minute
            }, mHour, mMinute, false
        )
        timePickerDialog.show()
    }
}

