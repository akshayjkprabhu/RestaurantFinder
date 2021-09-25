package com.ajp.dinein.domain.usecase.search

import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.usecase.UseCaseResult

interface SearchUseCase {
	suspend fun searchRestaurant(searchTerm : String) : UseCaseResult<List<Restaurant>>
}