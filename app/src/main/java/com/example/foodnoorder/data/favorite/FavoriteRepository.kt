package com.example.foodnoorder.data.favorite

import androidx.lifecycle.LiveData

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val favoriteItemList : LiveData<List<FavoriteItemModel>> = favoriteDao.getFavoriteItemList()

    suspend fun insertFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteDao.insertFavoriteItem(favoriteItemModel)
    }

    suspend fun updateFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteDao.updateFavoriteItem(favoriteItemModel)
    }

    suspend fun deleteFavoriteItem(favoriteItemModel: FavoriteItemModel){
        favoriteDao.deleteFavoriteItem(favoriteItemModel)
    }


}