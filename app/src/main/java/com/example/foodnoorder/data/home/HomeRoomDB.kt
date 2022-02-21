package com.example.foodnoorder.data.home

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HomeItemModel::class], version = 1, exportSchema = false)
abstract class HomeRoomDB : RoomDatabase() {

    abstract fun getHomeDao() : HomeDao

    companion object {
        @Volatile
        private var INSTANCE: HomeRoomDB? = null

        fun getHomeRoomDB(context: Context): HomeRoomDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HomeRoomDB::class.java,
                        "HomeRoomDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}