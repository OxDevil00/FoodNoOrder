package com.example.foodnoorder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodnoorder.R
import com.example.foodnoorder.data.home.HomeItemModel

class HomeAdapter(private val list : List<HomeItemModel>, private val homeItemListener: HomeItemListener)
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_food_item_view,parent,false)

        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = list[position]

        holder.foodPic.setImageResource(currentItem.food_pic)
        holder.foodName.text = currentItem.food_name
        holder.foodPrice.text = currentItem.food_price.toString()
        holder.foodLikeCount.text = currentItem.food_likes_count.toString()

        holder.homeAddToCartTxt.setOnClickListener {
            homeItemListener.onAddToCart(currentItem)
            holder.homeAddToCartTxt.visibility = View.GONE
        }
        holder.homePlaceOrderTxt.setOnClickListener {
            homeItemListener.onPlaceOrderClicked(currentItem)
        }
        holder.foodLikeImg.setOnClickListener {
            homeItemListener.onLikeBtnClicked(currentItem)
        }
        if (currentItem.isLiked){
            holder.foodLikeImg.setImageDrawable(ContextCompat.getDrawable(holder.foodLikeImg.context,R.drawable.ic_baseline_favorite_btn_24))
        }else if(!currentItem.isLiked){
            holder.foodLikeImg.setImageDrawable(ContextCompat.getDrawable(holder.foodLikeImg.context,R.drawable.ic_baseline_un_favorite_btn_24))
        }
    }
    inner class HomeViewHolder(viewItem : View):RecyclerView.ViewHolder(viewItem)  {
        val foodPic : ImageView = viewItem.findViewById(R.id.home_item_food_img)
        val foodName : TextView = viewItem.findViewById(R.id.home_item_food_name)
        val foodPrice : TextView = viewItem.findViewById(R.id.home_item_food_price)
        val foodLikeImg : ImageView = viewItem.findViewById(R.id.home_item_like_btn)
        val foodLikeCount : TextView = viewItem.findViewById(R.id.home_item_like_count)
        val homePlaceOrderTxt : TextView = viewItem.findViewById(R.id.home_item_place_order_txt)
        val homeAddToCartTxt : TextView = viewItem.findViewById(R.id.home_add_to_cart)

    }
}

interface HomeItemListener {
    fun onPlaceOrderClicked(homeItemModel: HomeItemModel)
    fun onLikeBtnClicked(homeItemModel: HomeItemModel)
    fun onAddToCart(homeItemModel: HomeItemModel)

}