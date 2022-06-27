package com.busraerpay.e_commerceappfinalproject.presentation.bag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.busraerpay.e_commerceappfinalproject.BR
import com.busraerpay.e_commerceappfinalproject.ProductsAdapter
import com.busraerpay.e_commerceappfinalproject.R
import com.busraerpay.e_commerceappfinalproject.databinding.ItemBagProductsBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import com.squareup.picasso.Picasso

class BagAdapter(val bagList: List<ProductsModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bagProductsBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_bag_products, parent,
            false)
        return BagViewHolder(bagProductsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BagViewHolder).OnBind(bagList.get(position))
    }

    override fun getItemCount(): Int {
        return  bagList.size
    }


    class BagViewHolder(val bagProductsBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(bagProductsBinding.root){

        fun OnBind( productsModel: ProductsModel) {

            val binding= bagProductsBinding as ItemBagProductsBinding
            Picasso.get().load(productsModel.image).into(binding.bagProductImage)
            binding.setVariable(BR.itemsbag,productsModel)

        }


    }
}