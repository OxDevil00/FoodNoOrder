package com.example.foodnoorder.data.payment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PaymentTable")
data class PaymentModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    @ColumnInfo(name = "totalFoodPayment")
    val totalFoodPayment : Int,
    @ColumnInfo(name = "deliveryCharge")
    val deliveryCharge : Int

)
