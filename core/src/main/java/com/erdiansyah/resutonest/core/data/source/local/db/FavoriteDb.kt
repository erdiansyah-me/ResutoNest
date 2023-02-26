package com.erdiansyah.resutonest.core.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erdiansyah.resutonest.core.data.source.local.db.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDb: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private  var INSTANCE: FavoriteDb? = null
        fun getInstance(context: Context): FavoriteDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDb::class.java, "favorite.db"
                ).build()
            }
    }
}