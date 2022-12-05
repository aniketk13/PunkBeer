package com.example.jumpingminds.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

@Entity(tableName="PunkBeer")
data class PunkEntity(
    @PrimaryKey(autoGenerate = true)

    val id:Int,
)