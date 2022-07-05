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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentBagBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import com.busraerpay.e_commerceappfinalproject.presentation.bag.BagAdapter
import com.busraerpay.e_commerceappfinalproject.presentation.bag.BagViewModel
import com.busraerpay.e_commerceappfinalproject.presentation.product.ProductsViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BagFragment : Fragment() {

    private lateinit var fragmentBagBinding: FragmentBagBinding
    private val bagViewModel by lazy { BagViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBagBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bag,container,false)
        return fragmentBagBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            fragmentBagBinding.bagButton.setOnClickListener {
                findNavController().navigate(R.id.action_bag_to_successFragment)
            }

            FirebaseAuth.getInstance().currentUser?.let {
                 bagViewModel.getBagProducts(it.uid)
                Log.v("POST","ürün geldi")
            }


        bagViewModel.productsList.observe(viewLifecycleOwner, Observer { bagList ->
            Log.v("Bag FRAGMENT", "$bagList")
            val adapter = BagAdapter(bagList) //adapter ü tanımladık
            fragmentBagBinding.setVariable(BR.adapter,adapter)

            //add price
            var start: Double = 0.0
            bagList.forEach { product ->
              val price =  product.price.toDouble()
                price.let {
                    start = (start + it)
                    fragmentBagBinding.bagTotalamount.text = start.toString() + "$"
                }
            }
        })

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        //layoutmanager i recycler ın içine set etmemiz gerkiyor.
        fragmentBagBinding.bagrecycler.layoutManager = linearLayoutManager


    }

}