package com.busraerpay.e_commerceappfinalproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel

/*
artık o interface DAO nun özelliklerini taşıyor.yani bir database
e bağlanıp oraya erişim sağlayan bir dao dir. interface artık bir
database e erişebilecek bir özelliğe sahip oldu.
Veritabanı fonksiyonlarına erişmek için OgrenciDAO adında interface bir sınıf oluşturuyoruz
Veritabanı üzerindeki tablolarımızda yapacağımız işlemleri temsil eden sınıf.Örn: Silme,Ekleme vb.
 */

@Dao
interface FavoritesProductsDAOInterface {

    @Insert
    fun addFavoriteProducts(favoritesProductsRoomModel: FavoritesProductsRoomModel)

    @Query("SELECT * FROM favoritesproductsdatabase")
    fun getFavoriteProducts(): List<FavoritesProductsRoomModel>?

    @Query("SELECT image FROM favoritesproductsdatabase")
    fun getFavoritesImage(): List<String>?


}