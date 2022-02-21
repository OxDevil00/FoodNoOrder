package com.example.foodnoorder.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodnoorder.R
import com.example.foodnoorder.data.favorite.FavoriteItemModel
import com.example.foodnoorder.data.home.HomeItemModel
import com.example.foodnoorder.data.myCart.MyCartModel
import com.example.foodnoorder.databinding.FragmentHomeBinding
import com.example.foodnoorder.ui.favorite.FavoriteViewModel
import com.example.foodnoorder.ui.myCart.MyCartActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeFragment : Fragment(), HomeItemListener {

    // This property is valid in only between onCreateView and onDestroyView.
    private lateinit var binding : FragmentHomeBinding
    lateinit var homeViewModel : HomeViewModel
    lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeRec : RecyclerView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        homeRec = binding.homeItemRec
        homeRec.layoutManager = LinearLayoutManager(activity)

        homeViewModel.homeItemList.observe(viewLifecycleOwner) {
            homeAdapter = HomeAdapter(it,this)
            homeRec.adapter = homeAdapter
        }
        homeViewModel.myCartListLive.observe(viewLifecycleOwner) {
            binding.homeCartCount.text = it.size.toString()
        }
        binding.homeCartIcon.setOnClickListener {
            startActivity(Intent(activity, MyCartActivity::class.java))
        }

        binding.homeUserImg.setOnClickListener {
            val homeList = ArrayList(listOf(
                HomeItemModel(0, R.drawable.smosa,"Samosa",20,56,"Samosa Is Testy",false),
                HomeItemModel(0, R.drawable.sadwitch2,"Sandwitch",15,66,"Sandwitch Is Testy",false),
                HomeItemModel(0, R.drawable.shakes,"Shake",30,26,"Shake Is Testy",false),
                HomeItemModel(0, R.drawable.shnakes,"Shnakes",36,62,"Shanakes Is Testy",false),
                HomeItemModel(0, R.drawable.bergger,"Bergger",25,44,"Burger Is Testy",false),
                HomeItemModel(0, R.drawable.dosa,"Dosa",15,45,"Dosa Is Testy",false),
                HomeItemModel(0, R.drawable.pakodi,"Pakodi",20,55,"Pakodi Is Testy",false),
                HomeItemModel(0, R.drawable.lichicipizza,"Pizza",220,561,"Pizza Is Testy",false)
            ))
            for (item in homeList) {
                GlobalScope.launch(Dispatchers.IO){
                    homeViewModel.insertHomeItem(item)
                }
            }
        }

        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onPlaceOrderClicked(homeItemModel: HomeItemModel) {
        GlobalScope.launch(Dispatchers.IO) {
            homeViewModel.insertCartItem(
                MyCartModel(
                    0,
                    homeItemModel.food_pic,
                    homeItemModel.food_name,
                    homeItemModel.food_price,
                    "This Is Very Resty",
                    1,
                    homeItemModel.food_price
                )
            )

            GlobalScope.launch(Dispatchers.Main){
                activity?.startActivity(Intent(activity,MyCartActivity::class.java))
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onLikeBtnClicked(homeItemModel: HomeItemModel) {
            if (!homeItemModel.isLiked){
                val favoriteItemModel = FavoriteItemModel(
                    0,
                    homeItemModel.food_pic,
                    homeItemModel.food_name,
                    homeItemModel.food_price,
                    homeItemModel.food_description,
                    true
                )
                GlobalScope.launch(Dispatchers.IO) {
                    favoriteViewModel.insertFavoriteItem(favoriteItemModel)
                    homeViewModel.updateHomeItem(
                        HomeItemModel(
                            homeItemModel.id,
                            homeItemModel.food_pic,
                            homeItemModel.food_name,
                            homeItemModel.food_price,
                            homeItemModel.food_likes_count + 1,
                            homeItemModel.food_description,
                            true
                        ))
                }

                Toast.makeText(activity, "You Liked ${homeItemModel.food_name}\n ${homeItemModel.food_name} Added To Favorites ", Toast.LENGTH_SHORT).show()
            }
            else if (homeItemModel.isLiked) {
                favoriteViewModel.favoriteItemList.observe(viewLifecycleOwner){
                    for (item in it){
                       if(item.food_name == homeItemModel.food_name){
                           GlobalScope.launch(Dispatchers.IO) {
                               favoriteViewModel.deleteFavoriteItem(item)
                           }
                       }
                    }
                }
                GlobalScope.launch(Dispatchers.IO){
                homeViewModel.updateHomeItem(
                    HomeItemModel(
                        homeItemModel.id,
                        homeItemModel.food_pic,
                        homeItemModel.food_name,
                        homeItemModel.food_price,
                        homeItemModel.food_likes_count - 1,
                        homeItemModel.food_description,
                        false
                    ))
                }
                Toast.makeText(activity,
                    "You Unliked ${homeItemModel.food_name}\n ${homeItemModel.food_name} Removed To Favorites ",
                    Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onAddToCart(homeItemModel: HomeItemModel) {
        GlobalScope.launch(Dispatchers.IO) {
            homeViewModel.insertCartItem(
                MyCartModel(
                    0,
                    homeItemModel.food_pic,
                    homeItemModel.food_name,
                    homeItemModel.food_price,
                    "This Is Very Resty",
                    1,
                    homeItemModel.food_price
                )
            )
            GlobalScope.launch(Dispatchers.Main){
                Toast.makeText(activity, "${homeItemModel.food_name} Added To Your Cart", Toast.LENGTH_SHORT).show()
            }
        }
    }
}