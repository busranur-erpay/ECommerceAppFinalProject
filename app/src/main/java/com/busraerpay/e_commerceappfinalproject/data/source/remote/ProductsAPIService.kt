package com.busraerpay.e_commerceappfinalproject.data.source.remote

import androidx.core.content.contentValuesOf
import com.busraerpay.e_commerceappfinalproject.domain.model.CRUDResponse
import com.busraerpay.e_commerceappfinalproject.domain.model.ProductsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ProductsAPIService {
    //burda base url yi oluşturcaz.
    private val BASE_URL = "https://canerture.com/api/ecommerce/"

    //şimdi apı ımızı oluşturcaz.retrofit.builder retrofit objesini oluş
    //turmamız için geliştiirlmiş bir yöntem.
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()) //json formatını modele çeviricez
        .build() //en sonunda da oluşturmak için bitirmek için bunu yazdık.JSON verilerinin erişilebilir nesneler haline dönüştürmek için kullanılır.
        .create(ProductsDAOInterface::class.java)
    //diyerek de hangi sınıftan oluşturacağımızı ,arayüzü burda belirtmeliyiz.

    //apı mızı oluşturduk.şimdi ise bir fonk yazalım ki bu fonk
//içinde bu yaptığımız üç adımın hepsine erişebilelim.


    fun getAllProducts() : Call<List<ProductsModel>> {
        return api.getAllProducts()
        //artık bunu döndüreerk ProductsAPIServis içersindeki sınıftan bu verileri alabiliirz.
        //ve böylece apiservice arayüzümü buraya bağladım ve bu sınıftan da bir obje oluşturduğumuz zaman artık bu getallproducts fonkununu
        //çağırıp bütün verileerimize ulaşabiliriz.
        //artık geri kalan hiçbir arayüzle uğreşmıcaz sadece ve sadece burdaki getallproducts fonk u ile işlemlerimimizi yapıcaz.
        //bu işlemleri ise VİEWMODEL içerisinde yapıcaz.
    }

    fun addToBag(user: String, title: String, price: Double, description: String,
                 category: String, image: String, rate:Float, count: Int, sale_state: Int ) : Call<CRUDResponse> {
        return api.addToBag(user, title, price, description, category, image, rate, count, sale_state)
    }

    fun getBagProducts(user: String) : Call<List<ProductsModel>> {
        return api.getBagProducts(user)
    }

    fun getCategories(): Call<List<String>>{
        return api.getCategories()
    }
}