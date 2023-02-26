package com.erdiansyah.resutonest.core.data

import com.erdiansyah.resutonest.core.data.source.local.db.FavoriteDao
import com.erdiansyah.resutonest.core.data.source.remote.RemoteDataSource
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.core.domain.model.RestaurantDetail
import com.erdiansyah.resutonest.core.domain.repository.IRestaurantRepository
import com.erdiansyah.resutonest.core.utils.asEntity
import com.erdiansyah.resutonest.core.utils.asModel
import com.erdiansyah.resutonest.core.utils.mapToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: FavoriteDao
): IRestaurantRepository {
    override fun getListRestaurant(): Flow<State<List<Restaurant>>> =
        flow<State<List<Restaurant>>> {
            emit(State.Loading())
            val data = remoteDataSource.getListRestaurant()
            data
                .onFailure {
                    emit(State.Error(it.message!!))
                }
                .onSuccess {
                if (it.error) {
                    emit(State.Error(it.message))
                } else {
                    emit(State.Success(it.restaurants.mapToDomain()))
                }
            }
        }.flowOn(Dispatchers.IO)

    override fun getDetailRestaurant(id: String): Flow<State<RestaurantDetail>> =
        flow<State<RestaurantDetail>> {
            emit(State.Loading())
            val data = remoteDataSource.getDetailRestaurant(id)
            data
                .onFailure {
                    emit(State.Error(it.message!!))
                }
                .onSuccess {
                    if (it.error) {
                        emit(State.Error(it.message))
                    } else {
                        emit(State.Success(it.restaurant.mapToDomain()))
                    }
                }
        }.flowOn(Dispatchers.IO)

    override fun getFavorite(): Flow<List<Restaurant>> {
        return dao.getFavorite().map {
            it.map {
                it.asModel()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertFavorite(restaurant: Restaurant) {
        return withContext(Dispatchers.IO) {
            dao.insertFavorite(restaurant.asEntity())
        }
    }

    override suspend fun deleteNonFavorite(id: String) {
        return withContext(Dispatchers.IO) {
            dao.deleteNonFav(id)
        }
    }

    override suspend fun checkFavorite(id: String) : Int {
        return withContext(Dispatchers.IO) {
            dao.checkFavorite(id)
        }
    }

}