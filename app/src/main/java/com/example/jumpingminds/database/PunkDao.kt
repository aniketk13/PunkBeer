package com.example.jumpingminds.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PunkDao {
    @Insert
    fun insertBeer(entity: PunkEntity)

    @Query("SELECT * FROM PunkBeer ORDER BY id DESC LIMIT 10")
    fun getAllBeers(): LiveData<List<PunkEntity>>
}