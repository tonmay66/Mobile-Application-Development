package com.example.e_commerceproductlistingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproductlistingapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private var allProducts = mutableListOf<Product>()
    private var displayedProducts = mutableListOf<Product>()
    private var isGridView = false
    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
        setupFilters()
        setupItemTouchHelper()
        
        loadInitialData()
    }

    private fun loadInitialData() {
        // Show skeleton (placeholder) items
        val skeletonData = mutableListOf(
            Product(-1, "Loading...", 0.0, 0f, "", 0),
            Product(-2, "Loading...", 0.0, 0f, "", 0),
            Product(-3, "Loading...", 0.0, 0f, "", 0)
        )
        adapter.submitList(skeletonData)

        // Simulate network delay
        Handler(Looper.getMainLooper()).postDelayed({
            isLoading = false
            setupProducts()
            updateAdapter()
        }, 2000)
    }

    private fun setupProducts() {
        allProducts = mutableListOf(
            Product(1, "Smartphone", 699.99, 4.5f, "Electronics", android.R.drawable.ic_menu_call),
            Product(2, "Laptop", 1200.00, 4.8f, "Electronics", android.R.drawable.ic_menu_gallery),
            Product(3, "T-Shirt", 19.99, 4.0f, "Clothing", android.R.drawable.ic_menu_edit),
            Product(4, "Jeans", 49.99, 4.2f, "Clothing", android.R.drawable.ic_menu_crop),
            Product(5, "Java Guide", 29.99, 4.7f, "Books", android.R.drawable.ic_menu_help),
            Product(6, "Kotlin Guide", 34.99, 4.9f, "Books", android.R.drawable.ic_menu_info_details),
            Product(7, "Pizza", 12.50, 4.6f, "Food", android.R.drawable.ic_menu_view),
            Product(8, "Burger", 8.99, 4.3f, "Food", android.R.drawable.ic_menu_slideshow),
            Product(9, "Action Figure", 15.00, 4.1f, "Toys", android.R.drawable.ic_menu_compass),
            Product(10, "Board Game", 25.00, 4.4f, "Toys", android.R.drawable.ic_menu_directions)
        )
        displayedProducts.clear()
        displayedProducts.addAll(allProducts)
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter { product ->
            if (product.id < 0) return@ProductAdapter // Ignore clicks on skeleton items
            product.inCart = !product.inCart
            adapter.notifyItemChanged(displayedProducts.indexOf(product))
            invalidateOptionsMenu()
        }
        updateLayoutManager()
        binding.recyclerView.adapter = adapter
    }

    private fun updateLayoutManager() {
        binding.recyclerView.layoutManager = if (isGridView) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        adapter.isGridView = isGridView
        adapter.notifyDataSetChanged()
    }

    private fun setupFilters() {
        binding.categoryChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            val category = when (checkedIds.firstOrNull()) {
                R.id.chipElectronics -> "Electronics"
                R.id.chipClothing -> "Clothing"
                R.id.chipBooks -> "Books"
                R.id.chipFood -> "Food"
                R.id.chipToys -> "Toys"
                else -> "All"
            }
            filterByCategory(category)
        }
    }

    private fun filterByCategory(category: String) {
        if (isLoading) return
        displayedProducts = if (category == "All") {
            allProducts.toMutableList()
        } else {
            allProducts.filter { it.category == category }.toMutableList()
        }
        updateAdapter()
    }

    private fun updateAdapter() {
        adapter.submitList(displayedProducts.toList())
        binding.emptyState.visibility = if (displayedProducts.isEmpty() && !isLoading) View.VISIBLE else View.GONE
    }

    private fun setupItemTouchHelper() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                Collections.swap(displayedProducts, fromPos, toPos)
                adapter.notifyItemMoved(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedProduct = displayedProducts[position]
                displayedProducts.removeAt(position)
                adapter.notifyItemRemoved(position)

                Snackbar.make(binding.recyclerView, "${deletedProduct.name} deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        displayedProducts.add(position, deletedProduct)
                        adapter.notifyItemInserted(position)
                    }.show()
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filterBySearch(newText)
                return true
            }
        })

        val cartItem = menu.findItem(R.id.action_cart)
        val cartCount = allProducts.count { it.inCart }
        cartItem.title = "Cart ($cartCount)"

        return true
    }

    private fun filterBySearch(query: String?) {
        if (isLoading) return
        displayedProducts = if (query.isNullOrEmpty()) {
            allProducts.toMutableList()
        } else {
            allProducts.filter { it.name.contains(query, ignoreCase = true) }.toMutableList()
        }
        updateAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle_view -> {
                isGridView = !isGridView
                item.setIcon(if (isGridView) android.R.drawable.ic_dialog_dialer else android.R.drawable.ic_menu_sort_by_size)
                updateLayoutManager()
                true
            }
            R.id.action_cart -> {
                val intent = Intent(this, CartActivity::class.java)
                val cartList = ArrayList(allProducts.filter { it.inCart })
                intent.putParcelableArrayListExtra("cart_items", cartList)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
