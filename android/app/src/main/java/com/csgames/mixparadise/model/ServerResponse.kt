package com.csgames.mixparadise.model

data class ServerResponse(
    val rating: Rating,
    val review: Review
)

data class Review(
    val strength: Int,
    val taste: Int,
    val volume: Int
)

data class Rating(
    val comment: String,
    val note: Int
)
