package com.csgames.mixparadise.model

import com.google.gson.annotations.SerializedName

class ServedDrinkResponse(val rating: Rating, val review: Review)

class Rating(@SerializedName("note") val score: Int, val comment: String)

class Review(val taste: Int, val volume: Int, val strength: Int)