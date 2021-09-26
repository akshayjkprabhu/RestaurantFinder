package com.ajp.dinein.data.dto

import com.google.gson.annotations.SerializedName

/**
 * RestaurantList
 */
data class RestaurantListResponse(
		@SerializedName("restaurants")
		val restaurants : List<RestaurantDTO>?
) : BaseResponse()

data class RestaurantDTO(
		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String,
		@SerializedName("cuisine_type") val cuisineType : String
)

data class MenuResponse(
		@SerializedName("menus")
		val menuList : List<RestaurantMenuDTO>? = null
)

data class RestaurantMenuDTO(
		@SerializedName("restaurantId")
		val restaurantId : Int,
		@SerializedName("categories")
		val categories : List<CategoryDTO>?
)

data class CategoryDTO(
		@SerializedName("id")
		val categoryId : Int,
		@SerializedName("name")
		val categoryName : String,
		@SerializedName("menu-items")
		val items : List<ItemDTO>?
)

data class ItemDTO(
		@SerializedName("id")
		val itemId : Int,
		@SerializedName("name")
		val itemName : String
)