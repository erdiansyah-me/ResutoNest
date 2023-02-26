package com.erdiansyah.resutonest.core.data.source.remote.network

import com.erdiansyah.resutonest.core.data.source.remote.response.RestaurantDetailResponse
import com.erdiansyah.resutonest.core.data.source.remote.response.RestaurantListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getRestaurantList(): Response<RestaurantListResponse>

    @GET("detail/{id}")
    suspend fun getRestaurantDetail(
        @Path("id") id: String
    ):Response<RestaurantDetailResponse>
}