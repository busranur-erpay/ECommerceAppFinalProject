package com.busraerpay.e_commerceappfinalproject.presentation.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busraerpay.e_commerceappfinalproject.data.repository.ProductsRepository
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel

class FavoritesViewModel(context: Context): ViewModel() {

    private var productsRepo = ProductsRepository(context)

    private var _isProductAddedFav = MutableLiveData<Boolean>()
    val isProductAddedFav: LiveData<Boolean>
        get() = _isProductAddedFav

    private var _favProductsList = MutableLiveData<List<FavoritesProductsRoomModel>>()
    val favProductsList: LiveData<List<FavoritesProductsRoomModel>>
        get() = _favProductsList


    init {
        getFavoriteProducts()
        _isProductAddedFav = productsRepo.isProductAddedFav

    }

    fun getFavoriteProducts(){
        productsRepo.getFavoriteProducts()
        _favProductsList = productsRepo.favProductsList

    }

    fun addFavoriteProducts(favProductsRoomModel: FavoritesProductsRoomModel){
        productsRepo.addFavoriteProducts(favProductsRoomModel)
    }
}