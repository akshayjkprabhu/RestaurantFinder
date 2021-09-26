package com.ajp.dinein.core.util

import android.util.Log

object Logger {
	
	private val isDebug = { true }
	
	fun d(tag : String, message : String) {
		if (isDebug()) Log.d(tag, message)
	}
}