package com.example.foodnoorder.data.myCart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyCartTable")
data class MyCartModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long,

    val foodImage : Int,
    val foodName : String,
    val foodPrice : Int,
    val foodDescription : String,
    val foodNumPieces : Int,
    val foodTotalPrice : Int

)