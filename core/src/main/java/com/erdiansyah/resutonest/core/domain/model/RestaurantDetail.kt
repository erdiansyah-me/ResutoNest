package com.erdiansyah.resutonest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantDetail(
    val address: String,
    val pictureId: String,
    val city: String,
    val name: String,
    val rating: Double,
    val description: String,
    val id: String,
    val menus: Menus
) : Parcelable

@Parcelize
data class Menus(
    val foods: List<Foods>,
    val drinks: List<Drinks>
) : Parcelable

@Parcelize
data class Foods(
    val name: String
) : Parcelable

@Parcelize
data class Drinks(
    val name: String
) : Parcelable