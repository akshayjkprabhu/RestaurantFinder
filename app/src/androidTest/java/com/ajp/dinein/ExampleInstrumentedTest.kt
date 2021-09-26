package com.ajp.dinein

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ajp.dinein.domain.model.Restaurant
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
	private var context : Context? = null
	
	@Before
	fun initialize() {
		context = InstrumentationRegistry.getInstrumentation().targetContext
		
	}
	
	@Test
	fun useAppContext() {
		// Context of the app under test.
		assertEquals("com.ajp.dinein", context?.packageName)
	}
	
	@After
	fun destroy() {
		context = null
	}
	
}