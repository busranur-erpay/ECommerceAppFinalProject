package com.busraerpay.e_commerceappfinalproject.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.busraerpay.e_commerceappfinalproject.data.source.local.FavoritesProductsDAOInterface
import com.busraerpay.e_commerceappfinalproject.data.source.local.FavoritesProductsRoomDatabase
import com.busraerpay.e_commerceappfinalproject.data.source.remote.ProductsAPIService
import com.busraerpay.e_commerceappfinalproject.data.source.remote.ProductsDAOInterface
import com.busraerpay.e_commerceappfinalproject.domain.model.CRUDResponse
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository (context: Context){

    var productsList = MutableLiveData<List<ProductsModel>>()

    private val productsApiService = ProductsAPIService()

    var favProductsList = MutableLiveData<List<FavoritesProductsRoomModel>>()

    var isProductAddedFav = MutableLiveData<Boolean>()

    var categoryList = MutableLiveData<List<String>>()

    private val favproductsDAOInterface: FavoritesProductsDAOInterface?=
        FavoritesProductsRoomDatabase.favoritesProductsRoomDatabase(context)?.favoritesProductsDAOInterface()

    fun getAllBooks(){
        productsApiService.getAllProducts().enqueue(object :Callback<List<ProductsModel>>{
            override fun onResponse(
                call: Call<List<ProductsModel>>,
                response: Response<List<ProductsModel>>
            ) {
                productsList.value = response.body()
            }

            override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                if (t.localizedMessage != null){
                    Log.e("Failure", t.localizedMessage!!)
                }
            }

        })
    }

    fun addToBag(user: String, title: String, price: Double, description: String,
                 category: String, image: String, rate:Float, count: Int, sale_state: Int ){
        productsApiService.addToBag(user, title, price, description, category, image, rate, count,
                                    sale_state).enqueue(object :Callback<CRUDResponse>{

            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                response.body()?.let {
                    Log.v("POST","Post işlemi yapıldı.")
                }
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                if (t.localizedMessage != null){
                    Log.e("POSTFailure", t.localizedMessage!!)
                }
            }

        })
    }

    fun getBagProducts(user: String){
        productsApiService.getBagProducts(user).enqueue(object : Callback<List<ProductsModel>> {

            override fun onResponse(
                call: Call<List<ProductsModel>>,
                response: Response<List<ProductsModel>>
            ) {
                response.body()?.let {
                    productsList.value = it
                }
            }

            override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                Log.v("Failure", t.localizedMessage!!)
            }

        })
    }

    fun getFavoriteProducts(){
        favproductsDAOInterface?.getFavoriteProducts()?.let {
            favProductsList.value = it
        }
    }

    fun addFavoriteProducts(favProductsRoomModel: FavoritesProductsRoomModel){
        favproductsDAOInterface?.getFavoritesImage()?.let {
            isProductAddedFav.value = if (it.contains(favProductsRoomModel.image).not()){
                favproductsDAOInterface.addFavoriteProducts(favProductsRoomModel)
                Log.v("favpro","$it")
                true
            }else{
                Log.v("favpro","fav eklenemedi")
                false
            }
        }

    }

    fun getCategories(){
        productsApiService.getCategories().enqueue(object : Callback<List<String>?>{
            override fun onResponse(call: Call<List<String>?>, response: Response<List<String>?>) {
                response.body()?.let {
                    categoryList.value = it
                    Log.v("category","$it")
                }
            }

            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                Log.v("category","${t.localizedMessage}")
            }

        })
    }
}



