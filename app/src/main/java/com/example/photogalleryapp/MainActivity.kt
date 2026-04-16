package com.example.photogalleryapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photogalleryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PhotoAdapter
    private val photos = mutableListOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupGridView()
        setupCategoryTabs()
        setupSelectionToolbar()

        binding.fabAdd.setOnClickListener {
            addRandomPhoto()
        }
    }

    private fun setupData() {
        // Nature
        photos.add(Photo(1, android.R.drawable.ic_menu_gallery, "Forest", "Nature"))
        photos.add(Photo(2, android.R.drawable.ic_menu_camera, "Mountain", "Nature"))
        // City
        photos.add(Photo(3, android.R.drawable.ic_menu_mapmode, "New York", "City"))
        photos.add(Photo(4, android.R.drawable.ic_menu_compass, "London", "City"))
        // Animals
        photos.add(Photo(5, android.R.drawable.ic_menu_mylocation, "Lion", "Animals"))
        photos.add(Photo(6, android.R.drawable.ic_menu_slideshow, "Elephant", "Animals"))
        // Food
        photos.add(Photo(7, android.R.drawable.ic_menu_month, "Pizza", "Food"))
        photos.add(Photo(8, android.R.drawable.ic_menu_week, "Burger", "Food"))
        // Travel
        photos.add(Photo(9, android.R.drawable.ic_menu_send, "Paris", "Travel"))
        photos.add(Photo(10, android.R.drawable.ic_menu_directions, "Tokyo", "Travel"))
        // More
        photos.add(Photo(11, android.R.drawable.ic_menu_info_details, "Beach", "Nature"))
        photos.add(Photo(12, android.R.drawable.ic_menu_agenda, "Skyline", "City"))
    }

    private fun setupGridView() {
        adapter = PhotoAdapter(this, photos) { count ->
            updateSelectionCount(count)
        }
        binding.gridView.adapter = adapter

        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            if (adapter.isSelectionMode()) {
                val photo = adapter.getItem(position)
                photo.isSelected = !photo.isSelected
                adapter.notifyDataSetChanged()
                updateSelectionCount(adapter.getSelectedCount())
            } else {
                val intent = Intent(this, FullscreenActivity::class.java)
                intent.putExtra(FullscreenActivity.EXTRA_IMAGE_RES, adapter.getItem(position).resourceId)
                startActivity(intent)
            }
        }

        binding.gridView.setOnItemLongClickListener { _, _, position, _ ->
            if (!adapter.isSelectionMode()) {
                adapter.setSelectionMode(true)
                binding.selectionToolbar.visibility = View.VISIBLE
                val photo = adapter.getItem(position)
                photo.isSelected = true
                adapter.notifyDataSetChanged()
                updateSelectionCount(1)
            }
            true
        }
    }

    private fun setupCategoryTabs() {
        binding.btnAll.setOnClickListener { adapter.filter("All") }
        binding.btnNature.setOnClickListener { adapter.filter("Nature") }
        binding.btnCity.setOnClickListener { adapter.filter("City") }
        binding.btnAnimals.setOnClickListener { adapter.filter("Animals") }
        binding.btnFood.setOnClickListener { adapter.filter("Food") }
        binding.btnTravel.setOnClickListener { adapter.filter("Travel") }
    }

    private fun setupSelectionToolbar() {
        binding.btnDelete.setOnClickListener {
            val count = adapter.getSelectedCount()
            adapter.removeSelected()
            exitSelectionMode()
            Toast.makeText(this, getString(R.string.photo_deleted_format, count), Toast.LENGTH_SHORT).show()
        }

        binding.btnShare.setOnClickListener {
            Toast.makeText(this, getString(R.string.sharing_format, adapter.getSelectedCount()), Toast.LENGTH_SHORT).show()
            exitSelectionMode()
        }
    }

    private fun updateSelectionCount(count: Int) {
        binding.tvSelectionCount.text = getString(R.string.selected_format, count)
        if (count == 0 && adapter.isSelectionMode()) {
            exitSelectionMode()
        }
    }

    private fun exitSelectionMode() {
        adapter.setSelectionMode(false)
        adapter.clearSelection()
        binding.selectionToolbar.visibility = View.GONE
    }

    private fun addRandomPhoto() {
        val newPhoto = Photo(
            photos.size + 1,
            android.R.drawable.ic_menu_report_image,
            "New Photo ${photos.size + 1}",
            "Nature"
        )
        photos.add(newPhoto)
        adapter.filter("All")
        Toast.makeText(this, getString(R.string.photo_added), Toast.LENGTH_SHORT).show()
    }
}