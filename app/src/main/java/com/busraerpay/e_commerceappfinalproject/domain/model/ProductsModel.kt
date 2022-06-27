package com.busraerpay.e_commerceappfinalproject.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsModel(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("user")
    @Expose
    val user: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("price")
    @Expose
    val price: Double,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("rate")
    @Expose
    val rate: Float,

    @SerializedName("count")
    @Expose
    val count: Int,

    @SerializedName("sale_state")
    @Expose
    val sale_state: Int

) : Parcelable