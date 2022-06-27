package com.busraerpay.e_commerceappfinalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentShopBinding
import com.busraerpay.e_commerceappfinalproject.presentation.bag.BagAdapter
import com.busraerpay.e_commerceappfinalproject.presentation.shop.ShopAdapter
import com.busraerpay.e_commerceappfinalproject.presentation.shop.ShopViewModel


class ShopFragment : Fragment() {
    private lateinit var fragmentShopBinding: FragmentShopBinding
    private val shopViewModel by lazy { ShopViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShopBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop,container,false)
        return fragmentShopBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        shopViewModel.categoryList.observe(viewLifecycleOwner, Observer { shopList ->
            Log.v("Bag FRAGMENT", "$shopList")
            val adapter = ShopAdapter(shopList) //adapter ü tanımladık
            fragmentShopBinding.setVariable(BR.adapter,adapter)
            //totalAmount(bagList)
        })
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        //layoutmanager i recycler ın içine set etmemiz gerkiyor.
        fragmentShopBinding.categoryRecycler.layoutManager = linearLayoutManager

    }

}