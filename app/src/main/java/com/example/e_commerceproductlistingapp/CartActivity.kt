package com.example.e_commerceproductlistingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceproductlistingapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: ProductAdapter
    private var cartItems: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadCartItems()
        updateTotalPrice()

        binding.btnCheckout.setOnClickListener {
            Toast.makeText(this, "Checkout successful!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter { product ->
            // In cart activity, "Add to Cart" button might act as remove or just show status
            // Requirement says show RecyclerView of cart items.
        }
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = adapter
    }

    private fun loadCartItems() {
        cartItems = intent.getParcelableArrayListExtra<Product>("cart_items") ?: mutableListOf()
        adapter.submitList(cartItems)
    }

    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.price }
        binding.totalPrice.text = String.format("$%.2f", total)
    }
}
