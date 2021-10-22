package com.example.sephora.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sephora.databinding.ItemImageBinding
import com.example.sephora.model.Product

class ProductImageAdapter (val context: Context, private val dataView: ProductImageDataView) :
    RecyclerView.Adapter<ProductImageViewHolder>() {

    private var images = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductImageViewHolder(context, binding, dataView)
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        if (images.isNotEmpty()) {
            holder.bindData(images[position])
        }

    }

    override fun getItemCount(): Int {
        return if (images.isNotEmpty())
            images.size
        else
            0
    }

    fun setImages(list: ArrayList<String>) {
        if (images.isNotEmpty()) images.clear()
        images.addAll(list)
    }
}