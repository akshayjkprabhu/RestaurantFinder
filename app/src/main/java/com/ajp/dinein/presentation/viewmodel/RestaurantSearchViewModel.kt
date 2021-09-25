package com.ajp.dinein.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ajp.dinein.core.base.BaseViewModel
import com.ajp.dinein.core.util.Logger
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
	
	fun searchRestaurantFor(searchText : String?) {
		
		viewModelScope.launch(Dispatchers.IO) {
			onMain {
				showProgressBar(true)
			}
			
			val result = searchUseCase.searchRestaurant("ABC")
			
			onMain {
				when (result) {
					is UseCaseResult.Success -> {
						result.data
					}
					is UseCaseResult.Failure -> {
						// Handle Error Here
					}
				}
				
			}
		}
	}
}