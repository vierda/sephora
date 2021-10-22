package com.example.sephora.ui.list

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sephora.R
import com.example.sephora.databinding.ItemProductBinding
import com.example.sephora.model.Product

class ProductListViewHolder(private val context: Context, private val binding: ItemProductBinding, private val dataView: ProductListDataView) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(entity: Product) {
        binding.title.text = entity.productName
        binding.description.text = entity.shortDescription

        binding.priceOffer.text = entity.originalPrice
        binding.priceOffer.paintFlags = binding.priceOffer.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.price.text = entity.price
        binding.discount.text = entity.discount
        binding.ratingBar.rating = entity.rating

        Glide.with(context).load(entity.productImages[0])
            .placeholder(android.R.drawable.alert_light_frame)
            .error(android.R.drawable.alert_light_frame)
            .into(binding.productImage)

        binding.productContainer.setOnClickListener {
           dataView.onProductClick(entity.productId, entity.productName)
        }
    }
}