package com.example.jumpingminds.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PunkBeer")
data class PunkEntity(
    @PrimaryKey(autoGenerate = true)

    val id: Int,
)