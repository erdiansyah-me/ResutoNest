package com.erdiansyah.resutonest.core.domain.usecase

import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantInteractor @Inject constructor(private val restaurantRepository: IRestaurantRepository):RestaurantUseCase {
    override fun getListRestaurant() = restaurantRepository.getListRestaurant()
    override fun getDetailRestaurant(id: String) = restaurantRepository.getDetailRestaurant(id)
    override suspend fun insertFavorite(restaurant: Restaurant) = restaurantRepository.insertFavorite(restaurant)
    override suspend fun deleteNonFav(id: String) = restaurantRepository.deleteNonFavorite(id)
    override suspend fun checkFavorite(id: String) = restaurantRepository.checkFavorite(id)
    override fun getFavorite(): Flow<List<Restaurant>> = restaurantRepository.getFavorite()
}