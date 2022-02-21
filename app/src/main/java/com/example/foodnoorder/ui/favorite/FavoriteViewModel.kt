package com.example.foodnoorder.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foodnoorder.data.favorite.FavoriteItemModel
import com.example.foodnoorder.data.favorite.FavoriteRepository
import com.example.foodnoorder.data.favorite.FavoriteRoomDB
import com.example.foodnoorder.ui.myCart.MyCartViewModel

class FavoriteViewModel(application: Application) : AndroidViewModel(application){

    private val favoriteDao = FavoriteRoomDB.getFavoriteRoomDB(application).getFavoriteDao()
    private val favoriteRepository =  FavoriteRepository(favoriteDao)

    val favoriteItemList : LiveData<List<FavoriteItemModel>> = favoriteRepository.favoriteItemList

    suspend fun insertFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteRepository.insertFavoriteItem(favoriteItemModel)
    }
    suspend fun updateFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteRepository.updateFavoriteItem(favoriteItemModel)
    }

    suspend fun deleteFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteRepository.deleteFavoriteItem(favoriteItemModel)
    }

    //work for cart
    val myCartViewModel = MyCartViewModel(application)



}