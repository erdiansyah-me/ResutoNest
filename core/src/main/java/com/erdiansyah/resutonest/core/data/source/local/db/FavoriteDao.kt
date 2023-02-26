package com.erdiansyah.resutonest.core.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erdiansyah.resutonest.core.data.source.local.db.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getFavorite(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    suspend fun deleteNonFav(id: String)

    @Query("SELECT count(*) FROM favorite WHERE favorite.id = :id")
    suspend fun checkFavorite(id: String) : Int
}