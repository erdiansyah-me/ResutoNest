package com.erdiansyah.resutonest.core.utils

import com.erdiansyah.resutonest.core.data.source.local.db.model.Favorite
import com.erdiansyah.resutonest.core.data.source.remote.response.*
import com.erdiansyah.resutonest.core.domain.model.*

    fun RestaurantsItem.asModel(): Restaurant =
            Restaurant(
                pictureId = pictureId,
                city = city,
                name = name,
                rating = rating,
                description = description,
                id = id
            )
    fun List<RestaurantsItem>.mapToDomain(): List<Restaurant> {
        return map { it.asModel() }
    }
    fun RestaurantResp.asModel(): RestaurantDetail =
        RestaurantDetail(
            pictureId = pictureId,
            city = city,
            address = address,
            id = id,
            rating = rating,
            description = description,
            name = name,
            menus = menus.asModel()
        )
    fun RestaurantResp.mapToDomain(): RestaurantDetail {
        return asModel()
    }

    fun MenusResp.asModel(): Menus =
        Menus(
            drinks = drinks.map { it.asModel() },
            foods = foods.map { it.asModel() }
        )

    fun DrinksItem.asModel(): Drinks = Drinks(name = name)
    fun FoodsItem.asModel(): Foods = Foods(name = name)
    fun Favorite.asModel(): Restaurant = Restaurant(
        pictureId = pictureId,
        city = city,
        name = name,
        rating = rating,
        description = description,
        id = id
    )
    fun Restaurant.asEntity(): Favorite = Favorite(
        pictureId = pictureId,
        city = city,
        name = name,
        rating = rating,
        description = description,
        id = id
    )