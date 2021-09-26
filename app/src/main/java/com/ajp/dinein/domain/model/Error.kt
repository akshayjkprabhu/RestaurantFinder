package com.ajp.dinein.domain.model

data class Error(
		val errorType : ErrorType,
		private val message : String? = null,
		private val exception : Exception? = null
) {
	fun message() : String {
		return if (message.isNullOrBlank()) errorType.error
		else message
	}
}

enum class ErrorType(val error : String, val code : Int) {
	COULD_NOT_FETCH(error = "Oops!! Something Went Wrong", 1001),
	NO_DATA_AVAILABLE("Could not fetch Restaurants", 1002),
	EMPTY_SEARCH_RESULTS("Matching Restaurant not found", 1003)
}