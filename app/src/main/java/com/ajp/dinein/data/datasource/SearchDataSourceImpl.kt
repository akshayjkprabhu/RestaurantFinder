package com.ajp.dinein.data.datasource

import android.content.Context
import com.ajp.dinein.core.util.JsonFileReader
import com.ajp.dinein.core.util.Logger
import com.ajp.dinein.data.dto.MenuResponse
import com.ajp.dinein.data.dto.RestaurantListResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchDataSourceImpl(
		val gson : Gson,
		val context : Context
) : SearchDataSource {
	/**
	 * parse restaurant list json file
	 */
	override suspend fun fetchRestaurantsAsync() : RestaurantListResponse {
		return withContext(Dispatchers.IO) {
			val jsonString = JsonFileReader.readFromAsset("restaurant_list.json", context)
			Logger.d(TAG, "fetchRestaurantsAsync - Json\n $jsonString")
			gson.fromJson(jsonString, RestaurantListResponse::class.java)
		}
	}
	
	/**
	 * parse menu list json file
	 */
	override suspend fun fetchMenuAsync() : MenuResponse {
		return withContext(Dispatchers.IO) {
			val jsonString = JsonFileReader.readFromAsset("menu_list.json", context)
			Logger.d(TAG, "fetchMenuAsync - Json\n $jsonString")
			gson.fromJson(jsonString, MenuResponse::class.java)
		}
	}
	
	
	companion object {
		const val TAG = "SearchDataSource"
	}
}