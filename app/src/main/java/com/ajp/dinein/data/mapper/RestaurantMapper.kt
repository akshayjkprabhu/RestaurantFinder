package com.ajp.dinein.data.mapper

import com.ajp.dinein.data.dto.MenuResponse
import com.ajp.dinein.data.dto.RestaurantListResponse
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.model.RestaurantMenu

internal fun RestaurantListResponse?.toListOfRestaurants() : List<Restaurant> {
	if (this == null || restaurants.isNullOrEmpty()) {
		return emptyList()
	}
	return restaurants.map {
		Restaurant(
				restaurantId = it.id,
				restaurantName = it.name,
				cuisineType = it.name
				/*it.cuisineType.split(",")*/
		)
	}
}

internal fun MenuResponse?.toMenuList() : List<RestaurantMenu> {
	return emptyList()
}