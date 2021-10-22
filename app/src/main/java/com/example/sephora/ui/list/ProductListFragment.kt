package com.example.sephora.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sephora.R
import com.example.sephora.databinding.FragmentProductListBinding
import com.example.sephora.ui.MainActivity
import com.example.sephora.ui.MainViewModel


class ProductListFragment : Fragment(), ProductListDataView {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        adapter = ProductAdapter(requireActivity(),this)
        binding.recyclerView.adapter = adapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = gridLayoutManager

        viewModel.productLiveData.observe(requireActivity(), {
            adapter.setProducts(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.fetchProduct()

        return binding.root
    }


    override fun onProductClick(productId: Int, productName:String) {
        val bundle = Bundle()
        bundle.putInt("productId", productId)
        bundle.putString("productName", productName)
        findNavController().navigate(R.id.action_list_to_detail, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}