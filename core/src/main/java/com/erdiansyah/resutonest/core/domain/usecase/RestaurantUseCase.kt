package com.erdiansyah.resutonest.core.domain.usecase

import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.model.RestaurantDetail
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getListRestaurant():Flow<State<List<Restaurant>>>
    fun getDetailRestaurant(id: String):Flow<State<RestaurantDetail>>
    suspend fun insertFavorite(restaurant: Restaurant)
    suspend fun deleteNonFav(id: String)
    suspend fun checkFavorite(id: String) : Int
    fun getFavorite(): Flow<List<Restaurant>>
}