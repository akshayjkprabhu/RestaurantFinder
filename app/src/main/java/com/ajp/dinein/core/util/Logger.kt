package com.ajp.dinein.core.util

import android.util.Log
import java.lang.Exception

object Logger {
	
	private val isDebug = { true }
	
	fun d(tag : String, message : String) {
		if (isDebug()) Log.d(tag, message)
	}
	
	fun d(tag : String, exception : Exception) {
		if (isDebug()) Log.d(tag, exception.localizedMessage ?: exception.toString())
	}
}