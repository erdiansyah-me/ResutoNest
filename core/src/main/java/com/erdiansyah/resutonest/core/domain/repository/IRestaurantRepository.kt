package com.erdiansyah.resutonest.core.domain.repository

import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.model.RestaurantDetail
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {
    fun getListRestaurant(): Flow<State<List<Restaurant>>>
    fun getDetailRestaurant(id: String): Flow<State<RestaurantDetail>>
    fun getFavorite(): Flow<List<Restaurant>>
    suspend fun insertFavorite(restaurant: Restaurant)
    suspend fun deleteNonFavorite(id: String)
    suspend fun checkFavorite(id: String) : Int
}