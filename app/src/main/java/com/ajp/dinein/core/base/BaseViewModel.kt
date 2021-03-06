package com.ajp.dinein.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
	
	val uiHelperLiveData = MutableLiveData<UIStateModel>().apply {
		UIStateModel()
	}
	
	/**
	 * Use this to run any block of code on Main thread
	 * Can use this method only within a coroutine scope
	 */
	protected suspend fun onMain(block : () -> Unit) {
		withContext(Dispatchers.Main) {
			block()
		}
	}
	
	/**
	 * Call this only on a main thread
	 */
	protected fun showProgressBar(show : Boolean) {
		uiHelperLiveData.value = UIStateModel(showProgressBar = show)
	}
	
	protected fun onError(message : String) {
		uiHelperLiveData.value = UIStateModel(errorMessage = message)
	}
	
	protected fun toastMessage(message : String) {
		uiHelperLiveData.value = UIStateModel(toastMessage = message)
	}
}