package com.ajp.dinein.domain.model

data class Restaurant(
		val restaurantId : Int,
		val restaurantName : String,
		val cuisineType : String
)

data class RestaurantMenu(
		val restaurantId : Int,
		val categories : List<Category>
)

data class Category(val catId : Int, val catName : String)

data class MenuItem(val itemId : Int, val itemName : String, val price : Double)


