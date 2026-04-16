package com.example.universityeventmanagementapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universityeventmanagementapp.databinding.ItemEventBinding
import com.example.universityeventmanagementapp.model.Event

class EventAdapter(
    private var events: List<Event>,
    private val onEventClick: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    fun updateList(newList: List<Event>) {
        events = newList
        notifyDataSetChanged()
    }

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, onEventClick: (Event) -> Unit) {
            binding.tvEventTitle.text = event.title
            binding.tvEventDate.text = "${event.date} | ${event.time}"
            binding.tvEventVenue.text = event.venue
            binding.tvEventSeats.text = "Seats Available: ${event.availableSeats}"
            binding.tvEventPrice.text = if (event.price == 0.0) "Free" else "$${event.price}"
            binding.ivEventBanner.setImageResource(event.imageRes)
            
            binding.root.setOnClickListener { onEventClick(event) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], onEventClick)
    }

    override fun getItemCount(): Int = events.size
}
