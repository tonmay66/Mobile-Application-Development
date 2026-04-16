package com.example.photogalleryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.photogalleryapp.databinding.ItemPhotoBinding

class PhotoAdapter(
    private val context: Context,
    private var photos: MutableList<Photo>,
    private val onItemSelected: (Int) -> Unit
) : BaseAdapter() {

    private var selectionMode = false
    private var filteredPhotos: List<Photo> = photos

    fun setSelectionMode(enabled: Boolean) {
        selectionMode = enabled
        notifyDataSetChanged()
    }

    fun isSelectionMode() = selectionMode

    fun filter(category: String) {
        filteredPhotos = if (category == "All") {
            photos
        } else {
            photos.filter { it.category == category }
        }
        notifyDataSetChanged()
    }

    fun removeSelected() {
        photos.removeAll { it.isSelected }
        filter("All") // Refresh the view
    }

    fun getSelectedCount() = photos.count { it.isSelected }

    fun clearSelection() {
        photos.forEach { it.isSelected = false }
        notifyDataSetChanged()
    }

    override fun getCount(): Int = filteredPhotos.size

    override fun getItem(position: Int): Photo = filteredPhotos[position]

    override fun getItemId(position: Int): Long = filteredPhotos[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemPhotoBinding
        val view: View

        if (convertView == null) {
            binding = ItemPhotoBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemPhotoBinding
            view = convertView
        }

        val photo = getItem(position)
        binding.ivPhoto.setImageResource(photo.resourceId)
        binding.tvTitle.text = photo.title
        binding.cbSelect.visibility = if (selectionMode) View.VISIBLE else View.GONE
        binding.cbSelect.isChecked = photo.isSelected

        binding.cbSelect.setOnClickListener {
            photo.isSelected = binding.cbSelect.isChecked
            onItemSelected(getSelectedCount())
        }

        return view
    }
}