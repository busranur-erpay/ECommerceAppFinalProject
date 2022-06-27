package com.busraerpay.e_commerceappfinalproject.data.source.remote

import com.busraerpay.e_commerceappfinalproject.domain.model.CRUDResponse
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDAOInterface {
    //fun getWeather(@Query("city") city: String, @Query("key") key: String): Call<WeatherModel>

    @GET("get_products.php")
    fun getAllProducts(): Call<List<ProductsModel>>

    @GET("get_categories.php")
    fun getCategories(): Call<List<String>>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: Float,
        @Field("count") count: Int,
        @Field("sale_state") sale_state: Int,
    ): Call<CRUDResponse>


    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProducts(
        @Field("user") user: String
    ): Call<List<ProductsModel>>
}