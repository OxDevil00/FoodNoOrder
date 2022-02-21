package com.example.foodnoorder.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodnoorder.R
import com.example.foodnoorder.data.favorite.FavoriteItemModel

class FavoriteAdapter(private val favoriteListener: FavoriteListener )
    : ListAdapter<FavoriteItemModel,FavoriteAdapter.FavoriteViewHolder>(MyDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_view, parent, false)

        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.foodPic.setImageResource(currentItem.food_pic)
        holder.foodName.text = currentItem.food_name
        holder.foodDescription.text = currentItem.food_description
        holder.foodPrice.text =currentItem.food_price.toString()

        holder.foodLikeBtn.setOnClickListener {
            favoriteListener.onHeartBtnClicked(currentItem)
        }
        holder.addToCartBtn.setOnClickListener {
            favoriteListener.onAddToCartClicked(currentItem)
        }
    }

    inner class FavoriteViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val foodPic: ImageView = viewItem.findViewById(R.id.favorite_food_img)
        val foodName: TextView = viewItem.findViewById(R.id.favorite_food_name)
        val foodDescription: TextView = viewItem.findViewById(R.id.favorite_food_description)
        val foodPrice: TextView = viewItem.findViewById(R.id.favorite_food_price)
        val foodLikeBtn: ImageView = viewItem.findViewById(R.id.favorite_heart_btn)
        val addToCartBtn : Button = viewItem.findViewById(R.id.favorite_add_to_cart_btn)

    }
}
class MyDiffUtil : DiffUtil.ItemCallback<FavoriteItemModel>(){
    override fun areItemsTheSame(oldItem: FavoriteItemModel, newItem: FavoriteItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FavoriteItemModel, newItem: FavoriteItemModel): Boolean {
        return oldItem == newItem
    }

}

interface FavoriteListener{
    fun onHeartBtnClicked(favoriteItemModel: FavoriteItemModel)
    fun onAddToCartClicked(favoriteItemModel: FavoriteItemModel)
}