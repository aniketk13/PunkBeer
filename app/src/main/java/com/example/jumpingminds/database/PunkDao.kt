package com.example.jumpingminds.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PunkDao {
    @Insert
    fun insertBeer(entity: PunkEntity)

    @Query("SELECT * FROM PunkBeer ORDER BY id DESC LIMIT 10")
    fun getAllBeers(): LiveData<List<PunkEntity>>

    @Query("SELECT COUNT() FROM PunkBeer WHERE beerId=:beerId")
    fun searchBeer(beerId:Int):Int

    @Query("DELETE FROM PunkBeer WHERE beerId=:beerId")
    fun deleteQuery(beerId:Int)
}