package com.example.sephora.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sephora.model.Product
import com.example.sephora.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    private val productLD = MutableLiveData<ArrayList<Product>>()
    private val productDetailLD = MutableLiveData<Product>()

    val productLiveData: MutableLiveData<ArrayList<Product>>
        get() = productLD

    val productDetailLiveData: MutableLiveData<Product>
        get() = productDetailLD

    fun fetchProduct () {
        viewModelScope.launch {
            mainRepository.fetchProducts().collect {
                productLD.postValue(it)
            }
        }
    }

    fun getProductById (productId:Int) {
        viewModelScope.launch (Dispatchers.IO) {
            productDetailLiveData.postValue(mainRepository.getProductById(productId))
        }
    }
}