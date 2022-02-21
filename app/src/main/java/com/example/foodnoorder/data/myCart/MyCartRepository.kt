package com.example.foodnoorder.data.myCart

import androidx.lifecycle.LiveData

class MyCartRepository(private val myCartDao: MyCartDao){

    val myCartListLive : LiveData<List<MyCartModel>> = myCartDao.getMyCartList()

    suspend fun insertCartItem(myCartModel: MyCartModel){
        myCartDao.insertCartItem(myCartModel)
    }

    suspend fun updateCartItem(myCartModel: MyCartModel){
        myCartDao.updateCartItem(myCartModel)
    }

    suspend fun deleteCartItem(myCartModel: MyCartModel){
        myCartDao.deleteCartItem(myCartModel)
    }

}