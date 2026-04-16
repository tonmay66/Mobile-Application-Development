package com.example.universityeventmanagementapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.universityeventmanagementapp.databinding.ActivityEventDetailBinding
import com.example.universityeventmanagementapp.model.Event
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = intent.getParcelableExtra<Event>("event")
        event?.let {
            setupUI(it)
            startCountdown(it.date)
        }
    }

    private fun startCountdown(dateString: String) {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        try {
            val eventDate = sdf.parse(dateString)
            val currentTime = System.currentTimeMillis()
            val diff = (eventDate?.time ?: 0) - currentTime

            if (diff > 0) {
                countDownTimer = object : CountDownTimer(diff, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                        val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                        binding.tvCountdown.text = String.format(Locale.getDefault(), "%02d d : %02d h : %02d m : %02d s", days, hours, minutes, seconds)
                    }

                    override fun onFinish() {
                        binding.tvCountdown.text = "Event Started!"
                    }
                }.start()
            } else {
                binding.tvCountdown.text = "Event Passed"
            }
        } catch (e: Exception) {
            binding.tvCountdown.text = "TBD"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    private fun setupUI(event: Event) {
        binding.collapsingToolbar.title = event.title
        binding.tvDetailTitle.text = event.title
        binding.tvDetailDate.text = "${event.date} | ${event.time}"
        binding.tvDetailVenue.text = event.venue
        binding.tvDetailDescription.text = event.description
        binding.ivDetailHeader.setImageResource(event.imageRes)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SeatBookingActivity::class.java)
            intent.putExtra("event", event)
            startActivity(intent)
        }
    }
}
