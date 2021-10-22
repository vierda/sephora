package com.example.sephora.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sephora.databinding.ItemProductBinding
import com.example.sephora.model.Product

class ProductAdapter(val context: Context, private val dataView: ProductListDataView) :
    RecyclerView.Adapter<ProductListViewHolder>() {

    private var products = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductListViewHolder(context, binding, dataView)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        if (products.isNotEmpty()) {
            holder.bindData(products[position])
        }

    }

    override fun getItemCount(): Int {
        return if (products.isNotEmpty())
            products.size
        else
            0
    }

    fun setProducts(list: ArrayList<Product>) {
        if (products.isNotEmpty()) products.clear()
        products.addAll(list)
    }
}