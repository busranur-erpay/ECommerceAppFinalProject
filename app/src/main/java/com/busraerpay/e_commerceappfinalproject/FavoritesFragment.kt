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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.busraerpay.e_commerceappfinalproject.data.source.local.FavoritesProductsRoomDatabase
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentFavoritesBinding
import com.busraerpay.e_commerceappfinalproject.presentation.bag.BagViewModel
import com.busraerpay.e_commerceappfinalproject.presentation.favorites.FavoritesAdapter
import com.busraerpay.e_commerceappfinalproject.presentation.favorites.FavoritesViewModel
import com.busraerpay.e_commerceappfinalproject.presentation.product.ProductsViewModel
import com.google.firebase.auth.FirebaseAuth

class FavoritesFragment : Fragment() {

    private lateinit var fragmentFavoritesBinding: FragmentFavoritesBinding

    //private lateinit var favoritesViewModel: FavoritesViewModel
    private val favoritesViewModel by lazy { FavoritesViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavoritesBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_favorites,container,false)

        return fragmentFavoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)


        favoritesViewModel.favProductsList.observe(viewLifecycleOwner, Observer { favList ->
            Log.e("HOME FRAGMENT", "$favList")
            val adapter = FavoritesAdapter(favList)
            fragmentFavoritesBinding.setVariable(BR.adapter,adapter)
        })


        val gridLayoutManager= GridLayoutManager(context, 2)
        //linearlayoutmanager tanımlıyoruz
       // val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        //layoutmanager i recycler ın içine set etmemiz gerkiyor.
        fragmentFavoritesBinding.favRecycler.layoutManager = gridLayoutManager

    }

}