package com.example.jumpingminds.api.models

data class Ingredients(
    val malt:ArrayList<Malt>,
    val hops:ArrayList<Hop>,
    val yeast:String
)

data class Hop(
    val name:String,
    val amount:Amount,
)

data class Malt(
    val name:String,
    val amount:Amount
)

data class Amount(
    val value:Double,
    val unit:String
)
