package com.erdiansyah.resutonest.core.data.source.remote

import com.erdiansyah.resutonest.core.data.source.remote.network.ApiService
import com.erdiansyah.resutonest.core.data.source.remote.response.RestaurantDetailResponse
import com.erdiansyah.resutonest.core.data.source.remote.response.RestaurantListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListRestaurant(): Result<RestaurantListResponse> {
        return try {
            val response = apiService.getRestaurantList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                   Result.failure(Exception("Kesalahan! Data Kosong"))
                }
            } else {
                Result.failure(Exception("Kesalahan! Data Gagal Dimuat"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Kesalahan! Tidak Dapat Menjangkau Data"))
        }
    }

    suspend fun getDetailRestaurant(id: String): Result<RestaurantDetailResponse> {
        return try {
            val response = apiService.getRestaurantDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Kesalahan! Data Kosong"))
                }
            } else {
                Result.failure(Exception("Kesalahan! Data Gagal Dimuat"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Kesalahan! Tidak Dapat Menjangkau Data"))
        }
    }
}