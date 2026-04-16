package com.example.newsarticlereaderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsarticlereaderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEventListeners()
    }

    private fun setupEventListeners() {
        // Quick Navigation Buttons
        binding.btnNavIntro.setOnClickListener {
            scrollToView(binding.tvHeadingIntro)
        }
        binding.btnNavKeyPoints.setOnClickListener {
            scrollToView(binding.tvHeadingKeyPoints)
        }
        binding.btnNavAnalysis.setOnClickListener {
            scrollToView(binding.tvHeadingAnalysis)
        }
        binding.btnNavConclusion.setOnClickListener {
            scrollToView(binding.tvHeadingConclusion)
        }

        // Back to Top Button
        binding.fabBackToTop.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0)
        }

        // Bookmark Button
        binding.btnBookmark.setOnClickListener {
            isBookmarked = !isBookmarked
            if (isBookmarked) {
                binding.btnBookmark.setImageResource(android.R.drawable.btn_star_big_on)
                Toast.makeText(this, "Article Bookmarked", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnBookmark.setImageResource(android.R.drawable.btn_star_big_off)
                Toast.makeText(this, "Bookmark Removed", Toast.LENGTH_SHORT).show()
            }
        }

        // Share Button
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check out this article")
                putExtra(Intent.EXTRA_TEXT, binding.tvTitle.text.toString())
            }
            startActivity(Intent.createChooser(shareIntent, "Share Article via"))
        }
    }

    private fun scrollToView(view: android.view.View) {
        binding.nestedScrollView.post {
            binding.nestedScrollView.smoothScrollTo(0, view.top)
        }
    }
}