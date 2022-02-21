package com.example.foodnoorder.ui.myCart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodnoorder.R
import com.example.foodnoorder.data.myCart.MyCartModel
import com.example.foodnoorder.databinding.ActivityMyCartBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyCartActivity : AppCompatActivity(),MyCartItemListener {
    private lateinit var binding : ActivityMyCartBinding
    lateinit var myCartViewModel : MyCartViewModel
    lateinit var myCartAdapter: MyCartAdapter
    private var totalFoodAmount = 0
    private var deliveryAmount = 0
    private var totalAmount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart)
        myCartViewModel = ViewModelProvider(this)[MyCartViewModel::class.java]

        myCartViewModel.myCartLiveList.observe(this){
            myCartAdapter = MyCartAdapter(this)
            myCartAdapter.submitList(it)
            binding.myCartRec.layoutManager = LinearLayoutManager(this)
            binding.myCartRec.adapter = myCartAdapter

            totalFoodAmount = getTotalFoodAmount(it)
            deliveryAmount = if (totalFoodAmount in 1..100){ 7 }else{ 0 }
            totalAmount = if (totalFoodAmount in 1..100){ totalFoodAmount + deliveryAmount }else{ totalFoodAmount }

            binding.myCartTotalFoodAmount.text = totalFoodAmount.toString()
            binding.myCartDeliveryAmount.text = deliveryAmount.toString()
            binding.myCartTotalAmount.text = totalAmount.toString()

            binding

        }


    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onDeleteBtnClicked(myCartModel: MyCartModel) {
        GlobalScope.launch(Dispatchers.IO){
            myCartViewModel.deleteCartItem(myCartModel)
            GlobalScope.launch(Dispatchers.Main){
                Toast.makeText(this@MyCartActivity, "${myCartModel.foodName} Deleted To Cart", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onMinusBtnClicked(myCartModel: MyCartModel) {
        if(myCartModel.foodNumPieces > 1){
            val updatedCartItem = MyCartModel(myCartModel.id,myCartModel.foodImage,myCartModel.foodName,
                myCartModel.foodPrice,myCartModel.foodDescription,myCartModel.foodNumPieces - 1,
                myCartModel.foodPrice * (myCartModel.foodNumPieces - 1))
            GlobalScope.launch(Dispatchers.IO){
                myCartViewModel.updateCartItem(updatedCartItem)
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onPlusBtnClicked(myCartModel: MyCartModel) {
        val updatedCartItem = MyCartModel(myCartModel.id,myCartModel.foodImage,myCartModel.foodName,
        myCartModel.foodPrice,myCartModel.foodDescription,myCartModel.foodNumPieces + 1,
        myCartModel.foodPrice * (myCartModel.foodNumPieces + 1))
        GlobalScope.launch(Dispatchers.IO){
            myCartViewModel.updateCartItem(updatedCartItem)
        }
    }
}

private fun getTotalFoodAmount(listOfMyCartModel : List<MyCartModel>): Int {
 var myTotalFoodAmount = 0
    for (item in listOfMyCartModel){
         myTotalFoodAmount += item.foodTotalPrice
    }
     return myTotalFoodAmount
}