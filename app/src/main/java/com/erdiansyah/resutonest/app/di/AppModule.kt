package com.erdiansyah.resutonest.app.di

import com.erdiansyah.resutonest.core.domain.usecase.RestaurantInteractor
import com.erdiansyah.resutonest.core.domain.usecase.RestaurantUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideRestaurantUseCase(restaurantInteractor: RestaurantInteractor): RestaurantUseCase
}