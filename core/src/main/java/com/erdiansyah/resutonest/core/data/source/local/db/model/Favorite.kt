package com.erdiansyah.resutonest.core.data.source.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite")
class Favorite(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "pictureId")
    var pictureId: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "rating")
    var rating: Double,
) : Serializable