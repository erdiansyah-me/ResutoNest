package com.erdiansyah.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val useCase: RestaurantUseCase): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown Class: ${modelClass.name}")
        }
    }
}