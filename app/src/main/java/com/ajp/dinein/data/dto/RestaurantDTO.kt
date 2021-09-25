package com.ajp.dinein.data.dto

import com.google.gson.annotations.SerializedName

/**
 * RestaurantList
 */
data class RestaurantListResponse(
		@SerializedName("restaurants")
		val restaurants : List<Restaurant>?
) : BaseResponse()

data class Restaurant(
		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String,
		@SerializedName("cuisine_type") val cuisineType : String
)

data class MenuResponse(
		val menuList : List<RestaurantMenu>? = null
)

data class RestaurantMenu(
		val restaurantId : Int
)

data class Category(val categoryId : Int)

data class Item(val itemId : Int)