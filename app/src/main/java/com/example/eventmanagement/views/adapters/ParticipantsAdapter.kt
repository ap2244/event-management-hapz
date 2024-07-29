package com.example.eventmanagement.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagement.databinding.ListItemParticipantBinding

class ParticipantsAdapter(
    private val participants: MutableList<String>,
    private val onRemoveClick: (String) -> Unit
) : RecyclerView.Adapter<ParticipantsAdapter.ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val binding = ListItemParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParticipantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = participants[position]
        with(holder.binding) {
            tvParticipantName.text = participant
            ivDelete.setOnClickListener {
                onRemoveClick(participant)
            }
        }
    }

    override fun getItemCount(): Int = participants.size

    fun addAll(mlist: ArrayList<String>) {
        participants.clear()
        participants.addAll(mlist)
        notifyDataSetChanged()
    }

    inner class ParticipantViewHolder(var binding: ListItemParticipantBinding) : RecyclerView.ViewHolder(binding.root)
}


