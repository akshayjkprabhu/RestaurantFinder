package com.ajp.dinein.core.base

data class UIStateModel(
		val showProgressBar : Boolean = false,
		val toastMessage : String? = null,
		val errorMessage : String? = null
) {
	fun enableMessageView() = showProgressBar || !errorMessage.isNullOrBlank()
}