package com.example.jumpingminds.api.models

import android.net.Uri
import java.net.URI

data class Beer(
    val id:String,
    val name:String,
    val first_brewed:String,
    val image_url:String,
    val abv:Double
)