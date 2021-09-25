package com.ajp.dinein.data.datasource

import com.ajp.dinein.data.dto.*
import com.ajp.dinein.domain.repo.RepositoryResult

interface SearchDataSource {
	suspend fun fetchRestaurantsAsync() : RestaurantListResponse
	
	suspend fun fetchMenuAsync() : MenuResponse
}