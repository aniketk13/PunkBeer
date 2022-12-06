package com.example.jumpingminds.api.models

data class Beer(
    val id: String,
    val name: String,
    val first_brewed: String,
    val image_url: String,
    val abv: Double
)