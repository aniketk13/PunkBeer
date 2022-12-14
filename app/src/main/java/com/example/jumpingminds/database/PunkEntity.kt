package com.example.jumpingminds.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PunkBeer")
data class PunkEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val beerId: Int,
    val name: String,
    val first_brewed: String,
    val image_url: String,
    val abv: Double
)