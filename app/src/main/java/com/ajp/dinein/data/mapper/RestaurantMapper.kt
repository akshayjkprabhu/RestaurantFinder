package com.ajp.dinein.data.mapper

import com.ajp.dinein.data.dto.*
import com.ajp.dinein.domain.model.Category
import com.ajp.dinein.domain.model.MenuItem
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.model.RestaurantMenu

/**
 * converts [RestaurantListResponse]  to list of [Restaurant]
 */
internal fun RestaurantListResponse?.toListOfRestaurants() : List<Restaurant> {
	if (this == null || restaurants.isNullOrEmpty()) {
		return emptyList()
	}
	return restaurants.map {
		Restaurant(
				restaurantId = it.id,
				restaurantName = it.name,
				cuisineType = it.cuisineType,
				image = it.image,
				reviewCount = 3,
				location = it.location
				/*it.cuisineType.split(",")*/
		)
	}
}

/**
 * converts [MenuResponse]  to list of [RestaurantMenu]
 */
internal fun MenuResponse?.toMenuList() : List<RestaurantMenu> {
	if (this == null || this.menuList == null) {
		return emptyList()
	}
	return menuList.map {
		it.toMenuItem()
	}
}

private fun RestaurantMenuDTO.toMenuItem() : RestaurantMenu {
	return RestaurantMenu(
			restaurantId = restaurantId,
			categories = categories?.map {
				it.toCategory()
			} ?: emptyList()
	)
}

private fun CategoryDTO.toCategory() : Category {
	return Category(
			catId = categoryId,
			catName = categoryName,
			
			items = items?.map {
				it.toMenuItem()
			} ?: emptyList()
	)
}

private fun ItemDTO.toMenuItem() : MenuItem {
	return MenuItem(
			itemId = itemId,
			itemName = itemName,
			price = 0.0
	)
}
