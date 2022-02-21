package com.example.foodnoorder.ui.oldOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.foodnoorder.R
import com.example.foodnoorder.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var binding : FragmentOrdersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_orders, container, false)




        return binding.root
    }


}