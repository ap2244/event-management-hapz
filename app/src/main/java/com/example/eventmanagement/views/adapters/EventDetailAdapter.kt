package com.example.eventmanagement.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagement.databinding.ListItemParticipantBinding
import com.example.eventmanagement.model.Event

class EventDetailAdapter(
    private var participants: ArrayList<String>
) : RecyclerView.Adapter<EventDetailAdapter.ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val binding = ListItemParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParticipantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = participants[position]
        holder.bind(participant)
    }

    override fun getItemCount(): Int = participants.size

    fun addAll(mlist: ArrayList<String>) {
        participants.clear()
        participants.addAll(mlist)
        notifyDataSetChanged()
    }

    inner class ParticipantViewHolder(private val binding: ListItemParticipantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(participant: String) {
            binding.tvParticipantName.text = participant
            binding.ivDelete.visibility = View.GONE
        }
    }
}
