package com.ajp.dinein.core.util

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JsonFileReaderTest {
	
	private var context : Context? = null
	
	@Before
	fun initialize() {
		context = InstrumentationRegistry.getInstrumentation().targetContext
	}
	
	@Test
	fun wrongFileNameThrowsException() {
		try {
			JsonFileReader.readFromAsset("", context!!)
		} catch (e : Exception) {
			assert(e is JsonFileReader.JsonReaderException)
		}
	}
	
	@After
	fun destroy() {
		context = null
	}
}