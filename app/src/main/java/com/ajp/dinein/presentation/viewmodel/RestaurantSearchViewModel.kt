package com.ajp.dinein.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ajp.dinein.core.base.BaseViewModel
import com.ajp.dinein.core.util.Logger
import com.ajp.dinein.domain.model.Error
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.domain.usecase.UseCaseResult
import com.ajp.dinein.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RestaurantSearchViewModel(private val searchUseCase : SearchUseCase) : BaseViewModel() {
	companion object {
		const val TAG = "RestaurantSearchViewModel"
		const val SEARCH_DELAY = 600L
	}
	
	init {
		Logger.d(TAG, "RestaurantSearchViewModel : init")
	}
	
	val restaurantResults : MutableLiveData<List<Restaurant>> = MutableLiveData()
	
	private var searchTypingTimer : Timer = Timer()
	
	/**
	 * Ignore API call request is user is still typing
	 * for a delay of [SEARCH_DELAY]
	 */
	fun onSearchTextChanged(searchText : String?) {
		viewModelScope.launch(Dispatchers.IO) {
			onMain { showProgressBar(true) }
			
			searchTypingTimer.cancel()
			searchTypingTimer = Timer()
			searchTypingTimer.schedule(object : TimerTask() {
				override fun run() {
					searchForRestaurants(searchText)
				}
			}, SEARCH_DELAY)
		}
	}
	
	/**
	 * makes search API call and updates the result live data
	 */
	private fun searchForRestaurants(searchText : String?) {
		viewModelScope.launch(Dispatchers.IO) {
			onMain { showProgressBar(true) }
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
		restaurantResults.value = emptyList()
		onError(exception.message())
	}
	
	override fun onCleared() {
		super.onCleared()
		searchTypingTimer.cancel()
	}
}