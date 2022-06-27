package com.busraerpay.e_commerceappfinalproject.presentation.productDetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busraerpay.e_commerceappfinalproject.data.repository.ProductsRepository
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel

class ProductsDetailViewModel(context: Context): ViewModel()  {

    private var productsRepo = ProductsRepository(context)

    private var _productsList = MutableLiveData<List<ProductsModel>>()
    val productsList: LiveData<List<ProductsModel>>
        get() = _productsList


    fun addToBag(user: String, title: String, price: Double, description: String,
                 category: String, image: String, rate:Float, count: Int, sale_state: Int ){
        productsRepo.addToBag(user, title, price, description, category, image, rate, count, sale_state)
    }
}