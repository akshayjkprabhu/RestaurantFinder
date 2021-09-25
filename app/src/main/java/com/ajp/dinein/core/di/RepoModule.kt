package com.ajp.dinein.core.di

import com.ajp.dinein.data.repo.home.RestaurantRepositoryImpl
import com.ajp.dinein.domain.repo.RestaurantRepository
import org.koin.dsl.module


val repositoryModule = module {
	factory<RestaurantRepository> {
		RestaurantRepositoryImpl(dataSource = get())
	}
}
