package com.ajp.dinein.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ajp.dinein.core.base.BaseViewModel
import com.ajp.dinein.core.util.Logger
import com.ajp.dinein.domain.model.Error
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.usecase.search.SearchUseCase
import com.ajp.dinein.domain.usecase.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantSearchViewModel(private val searchUseCase : SearchUseCase) : BaseViewModel() {
	companion object {
		const val TAG = "RestaurantSearchViewModel"
	}
	
	init {
		Logger.d(TAG, "RestaurantSearchViewModel : init")
	}
	
	val restaurantResults : MutableLiveData<List<Restaurant>> = MutableLiveData()
	
	fun searchRestaurantFor(searchText : String?) {
		
		viewModelScope.launch(Dispatchers.IO) {
			onMain {
				showProgressBar(true)
			}
			
			val result = searchUseCase.searchRestaurant(searchText)
			
			onMain {
				showProgressBar(false)
				when (result) {
					is UseCaseResult.Success -> {
						restaurantResults.value = result.data
					}
					is UseCaseResult.Failure -> {
						handleSearchError(result.exception)
					}
				}
				
			}
		}
	}
	
	private fun handleSearchError(exception : Error) {
		when (exception.errorType) {
			// Handle differnt errors
		}
	}
}