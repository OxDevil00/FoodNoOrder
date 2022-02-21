package com.example.foodnoorder.data.myCart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyCartModel::class], version = 3, exportSchema = false)
abstract class MyCartRoomDB : RoomDatabase() {

    abstract fun myCartDao() : MyCartDao

    companion object{

        @Volatile
        private var instance : MyCartRoomDB? = null

        fun getMyCartRoodDB(context: Context) : MyCartRoomDB{
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext
                        ,MyCartRoomDB::class.java
                        ,"MyCartDB").build()
                }
            }

            return instance!!
        }

    }

}