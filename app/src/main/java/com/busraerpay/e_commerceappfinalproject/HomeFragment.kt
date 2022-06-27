package com.busraerpay.e_commerceappfinalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentHomeBinding
import com.busraerpay.e_commerceappfinalproject.presentation.favorites.FavoritesViewModel
import com.busraerpay.e_commerceappfinalproject.presentation.product.ProductsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
   private lateinit var fragmentHomeBinding: FragmentHomeBinding

    //private lateinit var productsViewModel: ProductsViewModel
    private val productsViewModel by lazy { ProductsViewModel(requireContext()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //BAĞLAMA
        //productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            productsViewModel.getAllProducts()
        }

        productsViewModel.productsList.observe(viewLifecycleOwner, Observer { productList ->
            Log.e("HOME FRAGMENT", "$productList")
            val adapter = ProductsAdapter(productList) //adapter ü tanımladık
            fragmentHomeBinding.setVariable(BR.adapter,adapter)
        })


        //linearlayoutmanager tanımlıyoruz
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        //layoutmanager i recycler ın içine set etmemiz gerkiyor.
        fragmentHomeBinding.recyclerView.layoutManager = linearLayoutManager


        fragmentHomeBinding.homeButton.setOnClickListener {
           // findNavController().navigate(R.id.action_homeFragment_to_shopFragment)


        }

    }


}