package com.ajp.dinein.domain.repo

import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.model.RestaurantMenu


interface RestaurantRepository {
	
	suspend fun fetchRestaurants() : RepositoryResult<List<Restaurant>>
	
	suspend fun fetchMenuItems() : RepositoryResult<List<RestaurantMenu>>
	
}