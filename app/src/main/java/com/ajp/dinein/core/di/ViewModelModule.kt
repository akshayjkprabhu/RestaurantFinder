package com.ajp.dinein.core.di

import com.ajp.dinein.presentation.viewmodel.RestaurantSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel {
		RestaurantSearchViewModel(searchUseCase = get())
	}
}