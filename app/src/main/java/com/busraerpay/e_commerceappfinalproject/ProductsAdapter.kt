package com.busraerpay.e_commerceappfinalproject


import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.busraerpay.e_commerceappfinalproject.databinding.ItemProductsBinding
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import com.squareup.picasso.Picasso


/*
class ın yanına  :RecyclerView.Adapter yazarak bir recycler adapter
olacağını belirtiyoruz. adapter <> tagları içine bir tip alıyor. bu tip
viewholder. yani görünüm tutucu bir sınıf istiyor bizden. bu taglar içine
tanımladığımız sınıfı adapter sınıfının içinde oluşturuyoruz. oluşan
bu sınıfın da ne olduğunu belirtmemiz gerekiyor. :recyclerView.viewholder()
yazarak onun bir view holder olduğunu belirtiyoruz.
 */
class ProductsAdapter(val productsList: List<ProductsModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //burada item_products xml ini bağladık
        //buradaki productsBinding bizim item_products xmlimiz ile görünümümüzün birbirine bağlanmasına yardımcı olacak.
        //bu fonk bir viewholder döndürmemizi istiyor. ve return e yazdığımızda bir görünüm istiyor. görünüme de oluşturduğumuz görünümü yani productsBinding
        //i veriyoruz. ve artık her şey bağlanmış oluyor.
            //burada layouta erişmek için parent dan yararlandık
        val productsBinding= DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),R.layout.item_products, parent,false)
        return ProductsViewHolder(productsBinding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //burada viewholder e direkt bir değişken ile ulaşabiliyoruz, holder ile
        (holder as ProductsViewHolder).OnBind(productsList.get(position))
        //bununla itemView içerisindeki farklı farklı yerlere ulaşabiliyoruz
        //biz burda bize verilen kahraman listesindeki isimleri textview
        //imizde göstermek istiyoruz. textviewimizin adı recyclerViewTextView
        //olsun.
        //get in içine 0,1 gibi yazarsak 0. indeksi gösterir hepsinde
        //1 yazarsak 1.indeksteki elemanı gösterir hepsinde.
        //position dersek dizinin ve listenin indeksleriyle uyumlu çalışır
        //0,1,2,3... diye gider.
    }

    override fun getItemCount(): Int {
        //size ı ne kadarsa o kadarlık bir recyclerView isteyebilcez.
        return productsList.size
    }

    /*
    view holder sınıfı aslında recycler olarak kullandığımız layoutu adapter a
bağlamak için kullandığımız bir arasınıf gibi bir şey.

ne zaman bir xml ile bir kodu birbirine bağlayacak olursak INFLATER
aklımıza gelsin.
     */

    class ProductsViewHolder(val productsBinding: ViewDataBinding)
        : RecyclerView.ViewHolder(productsBinding.root){


        fun OnBind(productsModel:ProductsModel){

            val binding= productsBinding as ItemProductsBinding
            //binding.clothesButton.setOnClickListener() ile butona tıklanma özelliği yapabilirz.
            Picasso.get().load(productsModel.image).into(binding.productImage)
            binding.productImage.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToProductDetailFragment(productsModel)
                it.findNavController().navigate(action)
            }
            binding.setVariable(BR.itemsproducts,productsModel)

            //setVariable(BR.clothesmodel,clothes) modele modelimizi
            //set ediyoruz. o modele set ettiğimiz zaman her item
            //da gelen değer o modele set olmuş oluyor. yani her list
            //içerisine gelen değer. productsList listesinde gelen her değer
            //ProductsViewHolder a gitmiş oluyor.

        }
    }

}