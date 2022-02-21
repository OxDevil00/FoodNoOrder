package com.example.foodnoorder.data.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HomeTable")
data class HomeItemModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val food_pic: Int,
    val food_name: String,
    val food_price: Int,
    val food_likes_count: Int,
    val food_description : String,
    var isLiked : Boolean

)
