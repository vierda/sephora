package com.example.sephora.ui.detail

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sephora.R
import com.example.sephora.databinding.FragmentProductDetailBinding
import com.example.sephora.ui.MainActivity
import com.example.sephora.ui.MainViewModel

class ProductDetailFragment : Fragment(), ProductImageDataView {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val arguments = requireArguments()
        val title = arguments.getString("productName")
        (activity as MainActivity).supportActionBar?.title = title

        val productId = arguments.getInt("productId")
        viewModel.getProductById(productId)
        viewModel.productDetailLiveData.observe(requireActivity(), {

            if (isAdded) {
                Glide.with(requireContext()).load(it.productImages[0])
                    .placeholder(android.R.drawable.alert_light_frame)
                    .error(android.R.drawable.alert_light_frame)
                    .into(binding.imageView2)

                adapter = ProductImageAdapter(requireActivity(),this)
                binding.recyclerView.adapter = adapter

                val linearLayoutManager = LinearLayoutManager(requireContext(), GridLayoutManager.HORIZONTAL,false)
                binding.recyclerView.layoutManager = linearLayoutManager

                adapter.setImages(ArrayList(it.productImages))
                adapter.notifyDataSetChanged()

                binding.title.text = it.productName
                binding.description.text = it.shortDescription

                binding.priceOffer.text = it.originalPrice
                binding.priceOffer.paintFlags =
                    binding.priceOffer.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                binding.price.text = it.price
                binding.discount.text = it.discount
                binding.ratingBar.rating = it.rating

                binding.functionValue.text = it.function
                binding.skinTypeValue.text = it.skinType
                binding.finishValue.text = it.finish
                binding.formulationValue.text = it.formulation

                binding.whatItIsValue.text = it.detailDescription
                binding.whatItDoesValue.text = it.longDetailDescription
            }

        })

        return binding.root
    }

    override fun onImageClick(imageUrl: String) {
        Glide.with(requireContext()).load(imageUrl)
            .placeholder(android.R.drawable.alert_light_frame)
            .error(android.R.drawable.alert_light_frame)
            .into(binding.imageView2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}