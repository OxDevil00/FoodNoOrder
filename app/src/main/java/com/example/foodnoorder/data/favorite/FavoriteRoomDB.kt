package com.example.foodnoorder.data.favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteItemModel::class], version = 1, exportSchema = false)
abstract class FavoriteRoomDB : RoomDatabase() {

    abstract fun getFavoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDB? = null

        fun getFavoriteRoomDB(context: Context): FavoriteRoomDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDB::class.java,
                        "FavoriteRoomDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}