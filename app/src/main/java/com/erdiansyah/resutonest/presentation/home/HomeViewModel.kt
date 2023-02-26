package com.erdiansyah.resutonest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(restaurantUseCase: RestaurantUseCase): ViewModel() {
    val restaurant: StateFlow<State<List<Restaurant>>> = restaurantUseCase.getListRestaurant().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        State.NoState()
    )
}