package com.example.foodnoorder.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foodnoorder.data.home.HomeItemModel
import com.example.foodnoorder.data.home.HomeItemRepository
import com.example.foodnoorder.data.home.HomeRoomDB
import com.example.foodnoorder.data.myCart.MyCartModel
import com.example.foodnoorder.data.myCart.MyCartRepository
import com.example.foodnoorder.data.myCart.MyCartRoomDB

class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val homeRoomDao = HomeRoomDB.getHomeRoomDB(application).getHomeDao()
    private val homeItemRepository = HomeItemRepository(homeRoomDao)

    val homeItemList : LiveData<List<HomeItemModel>> = homeItemRepository.myHomeItemList
    suspend fun insertHomeItem(homeItemModel: HomeItemModel){
        homeItemRepository.insertHomeItem(homeItemModel)
    }
    suspend fun updateHomeItem(homeItemModel: HomeItemModel){
        homeItemRepository.updateHomeItem(homeItemModel)
    }



    //work for cart
    private val myCartDao = MyCartRoomDB.getMyCartRoodDB(application).myCartDao()
    private val myCartRepository = MyCartRepository(myCartDao)

    suspend fun insertCartItem(myCartModel: MyCartModel){
        myCartRepository.insertCartItem(myCartModel)
    }
    val myCartListLive = myCartRepository.myCartListLive

    //work for favorite


}