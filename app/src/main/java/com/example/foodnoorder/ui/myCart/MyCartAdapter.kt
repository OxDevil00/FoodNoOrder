package com.example.foodnoorder.ui.myCart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodnoorder.R
import com.example.foodnoorder.data.myCart.MyCartModel

class MyCartAdapter(private val myCartItemListener: MyCartItemListener )
    : ListAdapter<MyCartModel,MyCartAdapter.MyCartViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_cart_item,parent,false)

        return MyCartViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.food_pic.setImageResource(currentItem.foodImage)
        holder.food_name.text = currentItem.foodName
        holder.food_description.text = currentItem.foodDescription
        holder.food_price.text = currentItem.foodTotalPrice.toString()
        holder.food_num_of_pieces.text = currentItem.foodNumPieces.toString()

        holder.food_item_delete_btn.setOnClickListener {
            myCartItemListener.onDeleteBtnClicked(currentItem)
        }
        holder.food_item_min_btn.setOnClickListener {
            myCartItemListener.onMinusBtnClicked(currentItem)
        }
        holder.food_item_plus_btn.setOnClickListener {
            myCartItemListener.onPlusBtnClicked(currentItem)
        }

    }

    inner class MyCartViewHolder(viewItem : View):RecyclerView.ViewHolder(viewItem)  {

        val food_pic : ImageView = viewItem.findViewById(R.id.my_cart_food_img)
        val food_name : TextView = viewItem.findViewById(R.id.my_cart_food_name)
        val food_description : TextView = viewItem.findViewById(R.id.my_cart_food_description)
        val food_price : TextView = viewItem.findViewById(R.id.my_cart_food_price)
        val food_num_of_pieces : TextView = viewItem.findViewById(R.id.my_cart_food_count)
        val food_item_min_btn : ImageView = viewItem.findViewById(R.id.my_cart_food_min_btn)
        val food_item_plus_btn : ImageView = viewItem.findViewById(R.id.my_cart_food_plus_btn)
        val food_item_delete_btn : ImageView = viewItem.findViewById(R.id.my_cart_cross_btn)

    }

}
class MyDiffUtil : DiffUtil.ItemCallback<MyCartModel>(){
    override fun areItemsTheSame(oldItem: MyCartModel, newItem: MyCartModel): Boolean {
        return oldItem.foodName == newItem.foodName
    }

    override fun areContentsTheSame(oldItem: MyCartModel, newItem: MyCartModel): Boolean {
        return oldItem == newItem
    }

}

interface MyCartItemListener{
    fun onDeleteBtnClicked(myCartModel: MyCartModel)
    fun onMinusBtnClicked(myCartModel: MyCartModel)
    fun onPlusBtnClicked(myCartModel: MyCartModel)
}