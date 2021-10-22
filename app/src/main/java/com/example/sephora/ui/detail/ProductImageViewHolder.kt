package com.example.sephora.ui.detail

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sephora.R
import com.example.sephora.databinding.ItemImageBinding

class ProductImageViewHolder(
    private val context: Context,
    private val binding: ItemImageBinding,
    private val dataView: ProductImageDataView
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(entity: String) {
        Glide.with(context).load(entity)
            .placeholder(android.R.drawable.alert_light_frame)
            .error(android.R.drawable.alert_light_frame)
            .into(binding.productImage)

        binding.productImage.setOnClickListener {
            dataView.onImageClick(entity)
        }
    }

}