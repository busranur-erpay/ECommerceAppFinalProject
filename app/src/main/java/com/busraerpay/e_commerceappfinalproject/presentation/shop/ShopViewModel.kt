package com.busraerpay.e_commerceappfinalproject.presentation.shop

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busraerpay.e_commerceappfinalproject.data.repository.ProductsRepository

class ShopViewModel(context: Context): ViewModel() {

    private var productsRepo = ProductsRepository(context)

    private var _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get() = _categoryList

    init {
        getCategories()
    }

    fun getCategories(){
        productsRepo.getCategories()
        _categoryList= productsRepo.categoryList
    }
}