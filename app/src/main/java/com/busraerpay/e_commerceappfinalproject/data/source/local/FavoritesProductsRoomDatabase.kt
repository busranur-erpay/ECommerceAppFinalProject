package com.busraerpay.e_commerceappfinalproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.busraerpay.e_commerceappfinalproject.domain.model.FavoritesProductsRoomModel

/*
Veritabanımızı temsil eden sınıftır.Bu sınıf sayesinde veritabanı üzerinde işlem yapabiliriz
 */

@Database(entities = [FavoritesProductsRoomModel::class], version = 1)
abstract class FavoritesProductsRoomDatabase  : RoomDatabase(){

    abstract fun favoritesProductsDAOInterface(): FavoritesProductsDAOInterface

    companion object {

        private var instance: FavoritesProductsRoomDatabase? = null

        fun favoritesProductsRoomDatabase(context: Context): FavoritesProductsRoomDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FavoritesProductsRoomDatabase::class.java,
                    "favoritesproductsdatabase.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }


    }
}