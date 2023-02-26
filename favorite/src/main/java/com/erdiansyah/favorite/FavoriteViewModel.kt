package com.erdiansyah.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel (restaurantUseCase: RestaurantUseCase): ViewModel() {
    val favorite: StateFlow<List<Restaurant>> = restaurantUseCase.getFavorite().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
}