package com.ajp.dinein.data.datasource

import com.ajp.dinein.data.dto.MenuResponse
import com.ajp.dinein.data.dto.RestaurantListResponse

interface SearchDataSource {
	suspend fun fetchRestaurantsAsync() : RestaurantListResponse
	
	suspend fun fetchMenuAsync() : MenuResponse
}