package com.erdiansyah.resutonest.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.model.RestaurantDetail
import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase): ViewModel() {
    fun restaurantDetail(id: String): StateFlow<State<RestaurantDetail>> {
        return restaurantUseCase.getDetailRestaurant(id).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            State.NoState()
        )
    }
    fun insertFavorite(restaurant: Restaurant) {
        viewModelScope.launch {
            restaurantUseCase.insertFavorite(restaurant)
        }
    }
    fun deleteNonFav(id: String) {
        viewModelScope.launch {
            restaurantUseCase.deleteNonFav(id)
        }
    }

    suspend fun checkFavorite(id: String) : Int {
        return restaurantUseCase.checkFavorite(id)
    }
}