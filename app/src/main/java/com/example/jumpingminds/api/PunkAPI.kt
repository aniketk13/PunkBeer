package com.example.jumpingminds.api

import com.example.jumpingminds.api.models.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkAPI {
    @GET("/v2/beers")
    suspend fun getAllBeer(): ArrayList<Beer>

    @GET("/v2/beers/random")
    suspend fun getRandomBeer(): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByBeerName(@Query("beer_name") beer_name: String): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByYeast(@Query("yeast") yeast: String): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByHops(@Query("hops") hops: String): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByMalt(@Query("malt") malt: String): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByFood(@Query("food") food: String): ArrayList<Beer>

    @GET("/v2/beers")
    suspend fun getBeerByABV(@Query("abv_gt") abv_gt: String): ArrayList<Beer>

    @GET("/v2/beers/")
    suspend fun getBeerById(@Query("ids") ids: String): ArrayList<Beer>
}