package com.ajp.dinein.application

import android.app.Application
import com.ajp.dinein.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RestaurantFinderApp : Application() {
	
	override fun onCreate() {
		super.onCreate()
		initialize()
	}
	
	private fun initialize() {
		initKoin()
	}
	
	private fun initKoin() {
		startKoin {
			androidLogger(Level.DEBUG)
			androidContext(this@RestaurantFinderApp)
			modules(
					viewModelModule
					+ useCaseModule
					+ repositoryModule
					+ dsModule
			)
		}
	}
	
}