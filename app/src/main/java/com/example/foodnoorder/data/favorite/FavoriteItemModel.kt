package com.example.foodnoorder.data.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteTable")
data class FavoriteItemModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val food_pic: Int,
    val food_name: String,
    val food_price: Int,
    val food_description : String,
    val isLiked: Boolean
)
