package com.example.foodnoorder.data.payment

import androidx.lifecycle.LiveData

class PaymentRepository (private val paymentDao: PaymentDao) {

//    val deliveryCharge = paymentDao.getDeliveryCharge()

    val totalFoodPayment = paymentDao.getTotalFoodPayment()

    suspend fun insertPaymentAmount(paymentModel: PaymentModel){
        paymentDao.insertPaymentAmount(paymentModel)
    }

    suspend fun updatePaymentAmount(paymentModel: PaymentModel){
        paymentDao.updatePaymentAmount(paymentModel)

    }

    suspend fun deletePaymentAmount(paymentModel: PaymentModel){
        paymentDao.deletePaymentAmount(paymentModel)

    }

}