package com.busraerpay.e_commerceappfinalproject.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CRUDResponse(
    @SerializedName("status")
    @Expose
    val status: Int,

    @SerializedName("message")
    @Expose
    val message: String?
)