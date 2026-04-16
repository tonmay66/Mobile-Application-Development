package com.example.universityeventmanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universityeventmanagementapp.adapter.EventAdapter
import com.example.universityeventmanagementapp.databinding.ActivityEventsListBinding
import com.example.universityeventmanagementapp.model.Event
import com.google.android.material.chip.Chip

class EventsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsListBinding
    private lateinit var eventAdapter: EventAdapter
    private lateinit var allEvents: List<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allEvents = getSampleEvents()
        eventAdapter = EventAdapter(allEvents) { event ->
            val intent = Intent(this, EventDetailActivity::class.java)
            intent.putExtra("event", event)
            startActivity(intent)
        }

        binding.recyclerViewEvents.apply {
            layoutManager = LinearLayoutManager(this@EventsListActivity)
            adapter = eventAdapter
        }

        setupFilters()
    }

    private fun setupFilters() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterEvents()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterEvents()
                return true
            }
        })

        binding.chipGroupCategories.setOnCheckedChangeListener { group, checkedId ->
            filterEvents()
        }
    }

    private fun filterEvents() {
        val query = binding.searchView.query.toString().lowercase()
        val checkedChipId = binding.chipGroupCategories.checkedChipId
        val selectedCategory = if (checkedChipId != -1) {
            binding.root.findViewById<Chip>(checkedChipId).text.toString()
        } else {
            "All"
        }

        val filteredList = allEvents.filter { event ->
            val matchesQuery = event.title.lowercase().contains(query) || 
                             event.venue.lowercase().contains(query)
            val matchesCategory = selectedCategory == "All" || event.category == selectedCategory
            
            matchesQuery && matchesCategory
        }
        eventAdapter.updateList(filteredList)
    }

    private fun getSampleEvents(): List<Event> {
        return listOf(
            Event(1, "Tech Symposium 2026", "Oct 25, 2026", "10:00 AM", "Main Auditorium", "Tech", "Annual technology symposium featuring guest speakers and workshops.", 50.0, 200, 150, android.R.drawable.ic_menu_gallery),
            Event(2, "Inter-University Sports Meet", "Nov 05, 2026", "08:00 AM", "Sports Complex", "Sports", "Annual sports competition between various universities.", 20.0, 500, 300, android.R.drawable.ic_menu_gallery),
            Event(3, "Cultural Night", "Nov 12, 2026", "06:00 PM", "Open Air Theater", "Cultural", "A night full of music, dance, and cultural performances.", 30.0, 1000, 750, android.R.drawable.ic_menu_gallery),
            Event(4, "Academic Seminar", "Dec 01, 2026", "11:00 AM", "Seminar Hall A", "Academic", "Seminar on the future of AI and Machine Learning.", 0.0, 100, 20, android.R.drawable.ic_menu_gallery),
            Event(5, "Social Mixer", "Dec 10, 2026", "05:00 PM", "Student Lounge", "Social", "Meet and greet event for new students.", 10.0, 50, 45, android.R.drawable.ic_menu_gallery),
            Event(6, "Coding Challenge", "Jan 15, 2027", "09:00 AM", "Computer Lab 1", "Tech", "Competitive programming contest.", 15.0, 80, 60, android.R.drawable.ic_menu_gallery),
            Event(7, "Art Exhibition", "Feb 20, 2027", "10:00 AM", "Art Gallery", "Cultural", "Exhibition showcasing student artwork.", 5.0, 150, 100, android.R.drawable.ic_menu_gallery),
            Event(8, "Career Fair", "Mar 10, 2027", "10:00 AM", "Exhibition Center", "Academic", "Connect with potential employers.", 0.0, 300, 250, android.R.drawable.ic_menu_gallery)
        )
    }
}
