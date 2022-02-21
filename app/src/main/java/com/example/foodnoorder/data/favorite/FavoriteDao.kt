package com.example.foodnoorder.data.favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteItem(favoriteItemModel: FavoriteItemModel)

    @Delete
    suspend fun deleteFavoriteItem(favoriteItemModel: FavoriteItemModel)

    @Update
    suspend fun updateFavoriteItem(favoriteItemModel: FavoriteItemModel)

    @Query("SELECT * FROM FavoriteTable")
    fun getFavoriteItemList() : LiveData<List<FavoriteItemModel>>

}