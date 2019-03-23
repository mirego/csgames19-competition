package com.csgames.mixparadise.model.api

data class ServeResponse(val rating: Rating, val review: Review)

data class Rating(val note: Int, val comment: String)
data class Review(val taste: Int, val volume: Int, val strength: Int)
