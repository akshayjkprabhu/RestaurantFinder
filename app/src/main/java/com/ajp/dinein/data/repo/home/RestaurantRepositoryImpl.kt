package com.ajp.dinein.data.repo.home

import com.ajp.dinein.core.util.JsonFileReader
import com.ajp.dinein.core.util.Logger
import com.ajp.dinein.data.datasource.SearchDataSource
import com.ajp.dinein.data.mapper.toListOfRestaurants
import com.ajp.dinein.data.mapper.toMenuList
import com.ajp.dinein.domain.model.Error
import com.ajp.dinein.domain.model.ErrorType
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.model.RestaurantMenu
import com.ajp.dinein.domain.repo.RepositoryResult
import com.ajp.dinein.domain.repo.RestaurantRepository
import java.lang.Exception

class RestaurantRepositoryImpl(private val dataSource : SearchDataSource) : RestaurantRepository {
	
	override suspend fun fetchRestaurants() : RepositoryResult<List<Restaurant>> {
		return try {
			val res = dataSource.fetchRestaurantsAsync()
			RepositoryResult.Success(res.toListOfRestaurants())
			// check all the cases in error handling
		} catch (e : JsonFileReader.JsonReaderException) {
			Logger.d(TAG, "Exception : $e")
			RepositoryResult.Failure(Error((ErrorType.UNDEFINED)))
		} catch (e : Exception) {
			Logger.d(TAG, "Exception : $e")
			RepositoryResult.Failure(Error((ErrorType.UNDEFINED)))
		}
	}
	
	override suspend fun fetchMenuItems() : RepositoryResult<List<RestaurantMenu>> {
		return try {
			val res = dataSource.fetchMenuAsync()
			RepositoryResult.Success(res.toMenuList())
			// check all the cases in error handling
		} catch (e : JsonFileReader.JsonReaderException) {
			Logger.d(TAG, "Exception : $e")
			RepositoryResult.Failure(Error((ErrorType.UNDEFINED)))
		} catch (e : Exception) {
			Logger.d(TAG, "Exception : $e")
			RepositoryResult.Failure(Error((ErrorType.UNDEFINED)))
		}
	}
	
	companion object {
		const val TAG = "RestaurantRepository"
	}
}