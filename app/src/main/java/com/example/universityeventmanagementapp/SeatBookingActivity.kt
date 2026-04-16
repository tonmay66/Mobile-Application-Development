package com.example.universityeventmanagementapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.universityeventmanagementapp.databinding.ActivitySeatBookingBinding
import com.example.universityeventmanagementapp.model.Event
import kotlin.random.Random

class SeatBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeatBookingBinding
    private lateinit var event: Event
    private val totalSeats = 48
    private val seatStates = IntArray(totalSeats) // 0: Available, 1: Booked, 2: Selected
    private var selectedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        event = intent.getParcelableExtra("event") ?: return
        
        setupSeatGrid()
        updateSummary()

        binding.btnConfirmBooking.setOnClickListener {
            if (selectedCount > 0) {
                Toast.makeText(this, "Booking confirmed for $selectedCount seats!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Please select at least one seat", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSeatGrid() {
        // Randomly pre-book ~30%
        for (i in 0 until totalSeats) {
            if (Random.nextFloat() < 0.3f) {
                seatStates[i] = 1
            } else {
                seatStates[i] = 0
            }
        }

        val adapter = object : ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, seatStates.toTypedArray()) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getView(position, convertView, parent) as TextView
                view.text = "${position + 1}"
                view.gravity = android.view.Gravity.CENTER
                view.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                
                updateSeatView(view, seatStates[position])

                return view
            }
        }

        binding.seatGridView.adapter = adapter
        binding.seatGridView.setOnItemClickListener { _, view, position, _ ->
            if (seatStates[position] == 1) {
                Toast.makeText(this, "Seat already booked", Toast.LENGTH_SHORT).show()
                return@setOnItemClickListener
            }

            if (seatStates[position] == 0) {
                seatStates[position] = 2
                selectedCount++
            } else if (seatStates[position] == 2) {
                seatStates[position] = 0
                selectedCount--
            }

            updateSeatView(view as TextView, seatStates[position])
            updateSummary()
        }
    }

    private fun updateSeatView(view: TextView, state: Int) {
        val color = when (state) {
            0 -> android.R.color.holo_green_light
            1 -> android.R.color.holo_red_light
            2 -> android.R.color.holo_blue_light
            else -> android.R.color.holo_green_light
        }
        view.setBackgroundColor(ContextCompat.getColor(this, color))
    }

    private fun updateSummary() {
        binding.tvSummary.text = "$selectedCount seats selected"
        binding.tvTotalPrice.text = "Total: $${String.format("%.2f", selectedCount * event.price)}"
    }

    override fun onBackPressed() {
        if (selectedCount > 0) {
            AlertDialog.Builder(this)
                .setTitle("Discard Selection?")
                .setMessage("You have selected seats. Are you sure you want to go back?")
                .setPositiveButton("Yes") { _, _ -> super.onBackPressed() }
                .setNegativeButton("No", null)
                .show()
        } else {
            super.onBackPressed()
        }
    }
}
