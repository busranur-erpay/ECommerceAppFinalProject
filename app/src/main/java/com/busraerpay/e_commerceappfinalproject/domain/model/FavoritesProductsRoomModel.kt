package com.busraerpay.e_commerceappfinalproject.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//data modelini oluşturduk
@Entity(tableName = "favoritesproductsdatabase")
data class FavoritesProductsRoomModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "user")
    val user: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "rate")
    val rate: Float,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "sale_state")
    val sale_state: Int


)
