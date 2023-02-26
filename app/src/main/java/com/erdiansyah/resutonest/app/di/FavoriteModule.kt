package com.erdiansyah.resutonest.app.di

import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    fun provideUseCase(): RestaurantUseCase
}