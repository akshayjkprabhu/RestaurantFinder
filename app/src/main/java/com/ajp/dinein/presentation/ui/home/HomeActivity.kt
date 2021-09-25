package com.ajp.dinein.presentation.ui.home

import android.app.Activity
import android.os.Bundle
import com.ajp.dinein.presentation.viewmodel.RestaurantSearchViewModel
import org.koin.android.ext.android.inject

class HomeActivity : Activity() {
	
	 private val homeViewModel : RestaurantSearchViewModel by inject()
	
	companion object {
		const val TAG = "HomeActivity"
	}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		homeViewModel.searchRestaurantFor("")
	}
}