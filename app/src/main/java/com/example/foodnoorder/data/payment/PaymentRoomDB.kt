package com.example.foodnoorder.data.payment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PaymentModel::class], version = 1, exportSchema = false)
abstract class PaymentRoomDB : RoomDatabase() {

    abstract fun paymentDao() : PaymentDao

    companion object{

        @Volatile
        private var instance : PaymentRoomDB? = null

        fun getPaymentRoodDB(context: Context) : PaymentRoomDB {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext
                        , PaymentRoomDB::class.java
                        ,"PaymentRoomDB").build()
                }
            }

            return instance!!
        }

    }

}