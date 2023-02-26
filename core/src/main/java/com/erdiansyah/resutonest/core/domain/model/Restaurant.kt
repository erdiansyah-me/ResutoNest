package com.erdiansyah.resutonest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val pictureId: String,
    val city: String,
    val name: String,
    val rating: Double,
    val description: String,
    val id: String
) : Parcelable
