package com.example.e_commerceproductlistingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.e_commerceproductlistingapp.databinding.ItemProductGridBinding
import com.example.e_commerceproductlistingapp.databinding.ItemProductListBinding

class ProductAdapter(
    private val onAddToCart: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback()) {

    var isGridView: Boolean = false

    override fun getItemViewType(position: Int): Int {
        return if (isGridView) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_GRID) {
            val binding = ItemProductGridBinding.inflate(layoutInflater, parent, false)
            GridViewHolder(binding)
        } else {
            val binding = ItemProductListBinding.inflate(layoutInflater, parent, false)
            ListViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract class ProductViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(product: Product)
    }

    inner class ListViewHolder(private val binding: ItemProductListBinding) : ProductViewHolder(binding) {
        override fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productCategory.text = product.category
            binding.productPrice.text = "$${product.price}"
            binding.productRating.rating = product.rating
            binding.productImage.setImageResource(product.imageRes)
            binding.btnAddToCart.text = if (product.inCart) "In Cart" else "Add to Cart"
            binding.btnAddToCart.setOnClickListener { onAddToCart(product) }
        }
    }

    inner class GridViewHolder(private val binding: ItemProductGridBinding) : ProductViewHolder(binding) {
        override fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = "$${product.price}"
            binding.productImage.setImageResource(product.imageRes)
            binding.btnAddToCart.setImageResource(if (product.inCart) android.R.drawable.ic_delete else android.R.drawable.ic_input_add)
            binding.btnAddToCart.setOnClickListener { onAddToCart(product) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
    }

    companion object {
        private const val VIEW_TYPE_LIST = 0
        private const val VIEW_TYPE_GRID = 1
    }
}
