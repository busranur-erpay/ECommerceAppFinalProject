package com.busraerpay.e_commerceappfinalproject.presentation.bag

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busraerpay.e_commerceappfinalproject.data.repository.ProductsRepository
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel

class BagViewModel(context: Context): ViewModel() {

    private var productsRepo = ProductsRepository(context)

    private var _productsList = MutableLiveData<List<ProductsModel>>()
    val productsList: LiveData<List<ProductsModel>>
        get() = _productsList


    fun getBagProducts(user: String){
        productsRepo.getBagProducts(user)
        _productsList = productsRepo.productsList
    }
}