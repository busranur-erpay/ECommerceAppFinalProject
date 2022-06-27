package com.busraerpay.e_commerceappfinalproject.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.busraerpay.e_commerceappfinalproject.BR
import com.busraerpay.e_commerceappfinalproject.R
import com.busraerpay.e_commerceappfinalproject.databinding.ItemBagProductsBinding
import com.busraerpay.e_commerceappfinalproject.databinding.ItemFavProductsBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import com.squareup.picasso.Picasso


class FavoritesAdapter(val favList: List<FavoritesProductsRoomModel>):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val favoritesBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_fav_products, parent,
            false)
        return FavoritesViewHolder(favoritesBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoritesViewHolder).OnBind(favList.get(position))
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    class FavoritesViewHolder(val favoritesBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(favoritesBinding.root){

        fun OnBind( favProductsRoomModel: FavoritesProductsRoomModel) {

            val binding= favoritesBinding as ItemFavProductsBinding
            Picasso.get().load(favProductsRoomModel.image).into(binding.cardFavImage)
            binding.setVariable(BR.itemfavs,favProductsRoomModel)

        }


    }

}


