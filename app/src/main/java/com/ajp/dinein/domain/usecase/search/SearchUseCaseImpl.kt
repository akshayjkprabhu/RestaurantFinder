package com.ajp.dinein.domain.usecase.search

import com.ajp.dinein.domain.model.*
import com.ajp.dinein.domain.repo.RepositoryResult
import com.ajp.dinein.domain.repo.RestaurantRepository
import com.ajp.dinein.domain.usecase.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class SearchUseCaseImpl(private val restaurantRepo : RestaurantRepository) : SearchUseCase {
	
	override suspend fun searchRestaurant(searchTerm : String) : UseCaseResult<List<Restaurant>> {
		return withContext(Dispatchers.IO) {
			val restaurantCall = async {
				restaurantRepo.fetchRestaurants()
			}
			val menuCall = async {
				restaurantRepo.fetchMenuItems()
			}
			return@withContext try {
				val restaurantResult = restaurantCall.await()
				val menuResult = menuCall.await()
				onRestaurantResults(restaurantResult, menuResult, searchTerm)
			} catch (e : Exception) {
				UseCaseResult.Failure(Error(ErrorType.UNDEFINED))
			}
		}
	}
	
	private suspend fun onRestaurantResults(
			restaurantResult : RepositoryResult<List<Restaurant>>,
			menuResult : RepositoryResult<List<RestaurantMenu>>,
			searchTerm : String
	) : UseCaseResult<List<Restaurant>> {
		
		return when (restaurantResult) {
			is RepositoryResult.Success -> {
				when {
					restaurantResult.repositoryData.isEmpty() -> {
						UseCaseResult.Failure(Error(ErrorType.NO_DATA_AVAILABLE))
					}
					searchTerm.isBlank() -> {
						// If user has not searched yet, show the entire restaurant results
						UseCaseResult.Success(restaurantResult.repositoryData)
					}
					else -> {
						
						val menuList = (menuResult as? RepositoryResult.Success)?.repositoryData
						//Even if there is error in parsing menu List, need not show it to the user
						// as search will be done on the restaurant list
						return performSearch(restaurantResult.repositoryData, menuList, searchTerm)
					}
				}
			}
			
			is RepositoryResult.Failure -> {
				UseCaseResult.Failure(restaurantResult.error)
			}
		}
	}
	
	private suspend fun performSearch(
			restaurantList : List<Restaurant>,
			menuList : List<RestaurantMenu>?,
			searchTerm : String
	) : UseCaseResult<List<Restaurant>> {
		/**
		 * If the list it too long, then the calculation can take
		 * time, Move the logic into the Default Dispatcher
		 */
		return withContext(Dispatchers.Default) {
			/**
			 * Find all the restaurantIds which have matching item namse
			 */
			val restaurantIds = menuList?.filter {
				it.matches(searchTerm)
			}?.map {
				it.restaurantId
			}
			
			/**
			 * Get the final list by matching name / cuisine /
			 * item names (using already filtered [restaurantIds]
			 */
			val list = restaurantList.filter {
				it.matches(searchTerm) ||
				restaurantIds?.contains(it.restaurantId) ?: false
			}
			/**
			 * Return the Usecase results
			 */
			return@withContext if (list.isEmpty()) {
				UseCaseResult.Failure(Error(ErrorType.EMPTY_SEARCH_RESULTS))
			} else {
				UseCaseResult.Success(list)
			}
			
		}
	}
}