package com.example.foodnoorder.data.home

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHomeItem(homeItemModel: HomeItemModel)

    @Delete
    suspend fun deleteHomeItem(homeItemModel: HomeItemModel)

    @Update
    suspend fun updateHomeItem(homeItemModel: HomeItemModel)

    @Query("SELECT * FROM HomeTable")
    fun getHomeItemList() : LiveData<List<HomeItemModel>>

}