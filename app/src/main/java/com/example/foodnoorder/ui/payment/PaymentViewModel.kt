package com.example.foodnoorder.ui.payment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.foodnoorder.data.payment.PaymentRepository
import com.example.foodnoorder.data.payment.PaymentRoomDB

class PaymentViewModel(application: Application) : AndroidViewModel(application) {

    private val paymentDao = PaymentRoomDB.getPaymentRoodDB(application).paymentDao()
    private val paymentRepository = PaymentRepository(paymentDao)

//    val deliveryCharge = paymentRepository.deliveryCharge
    val foodTotalPayment = paymentRepository.totalFoodPayment
//    val totalPayment = paymentRepository.totalPayment

}