package com.example.foodnoorder.data.home

import androidx.lifecycle.LiveData

class HomeItemRepository(private val homeDao: HomeDao) {

    val myHomeItemList : LiveData<List<HomeItemModel>> = homeDao.getHomeItemList()

    suspend fun insertHomeItem(homeItemModel: HomeItemModel){
        homeDao.insertHomeItem(homeItemModel)
    }

    suspend fun updateHomeItem(homeItemModel: HomeItemModel){
        homeDao.updateHomeItem(homeItemModel)
    }

    suspend fun deleteHomeItem(homeItemModel: HomeItemModel){
        homeDao.deleteHomeItem(homeItemModel)
    }


}