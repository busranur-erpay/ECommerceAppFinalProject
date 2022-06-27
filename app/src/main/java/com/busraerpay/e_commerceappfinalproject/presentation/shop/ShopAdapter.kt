package com.busraerpay.e_commerceappfinalproject.presentation.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.busraerpay.e_commerceappfinalproject.BR
import com.busraerpay.e_commerceappfinalproject.R
import com.busraerpay.e_commerceappfinalproject.databinding.ItemBagProductsBinding
import com.busraerpay.e_commerceappfinalproject.databinding.ItemCategoriesBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import com.squareup.picasso.Picasso


class ShopAdapter(val categoryList: List<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val shopBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_categories, parent,
            false)
        return ShopViewHolder(shopBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShopViewHolder).OnBind(categoryList.get(position))
    }

    override fun getItemCount(): Int {
        return  categoryList.size
    }

    class ShopViewHolder(val shopBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(shopBinding.root) {

        fun OnBind(productsModel: String) {

            val binding = shopBinding as ItemCategoriesBinding
            binding.categoryText.text=productsModel
            binding.setVariable(BR.itemsbag, productsModel)

        }
    }
}