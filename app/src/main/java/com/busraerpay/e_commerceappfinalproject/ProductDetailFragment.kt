package com.busraerpay.e_commerceappfinalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentProductDetailBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel
import com.busraerpay.e_commerceappfinalproject.presentation.favorites.FavoritesViewModel
import com.busraerpay.e_commerceappfinalproject.presentation.productDetail.ProductsDetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var fragmentProductDetailBinding: FragmentProductDetailBinding
   // private lateinit var productsDetailViewModel: ProductsDetailViewModel
    //private lateinit var favoritesViewModel: FavoritesViewModel
    private val productsDetailViewModel by lazy { ProductsDetailViewModel(requireContext()) }
    private val favoritesViewModel by lazy { FavoritesViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProductDetailBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false)
        return fragmentProductDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragmentProductDetailBinding.detailBack.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_to_home)
        }

        val product = args.productModel
        fragmentProductDetailBinding.productModel = product
        Picasso.get().load(product.image).into(fragmentProductDetailBinding.detailImage)

        //BAĞLAMA
        //productsDetailViewModel = ViewModelProvider(this).get(ProductsDetailViewModel::class.java)


        fragmentProductDetailBinding.detailButton.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let { user ->
                productsDetailViewModel.addToBag(
                    user.uid,
                    product.title,
                    product.price,
                    product.description,
                    product.category,
                    product.image,
                    product.rate,
                    product.count,
                    product.sale_state
                )

            }
            Log.v("POST","post oldu")
            findNavController().navigate(R.id.action_productDetailFragment_to_bag)
        }

        fragmentProductDetailBinding.detailFavButton.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let { user ->
                favoritesViewModel.addFavoriteProducts(
                    FavoritesProductsRoomModel(
                        id = product.id,
                        user = user.uid,
                        title = product.title,
                        price = product.price,
                        description = product.description,
                        category = product.category,
                        image = product.image,
                        rate = product.rate,
                        count = product.count,
                        sale_state = product.sale_state
                    )
                )
            }
        }

    }

}