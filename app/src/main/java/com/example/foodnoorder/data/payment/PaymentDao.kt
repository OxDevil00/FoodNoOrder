package com.example.foodnoorder.data.payment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPaymentAmount(paymentModel: PaymentModel)

    @Update
    suspend fun updatePaymentAmount(paymentModel: PaymentModel)

    @Delete
    suspend fun deletePaymentAmount(paymentModel: PaymentModel)

    @Query("SELECT totalFoodPayment FROM PaymentTable ")
    fun getTotalFoodPayment() : LiveData<Int>

    @Query("SELECT deliveryCharge FROM PaymentTable ")
    fun getDeliveryCharge() : Int

}
