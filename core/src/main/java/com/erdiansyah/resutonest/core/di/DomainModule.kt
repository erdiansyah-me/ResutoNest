package com.erdiansyah.resutonest.core.di

import com.erdiansyah.resutonest.core.data.RestaurantRepository
import com.erdiansyah.resutonest.core.domain.repository.IRestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun provideRepository(restaurantRepository: RestaurantRepository): IRestaurantRepository
}