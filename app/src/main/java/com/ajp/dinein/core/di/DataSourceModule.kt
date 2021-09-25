package com.ajp.dinein.core.di

import com.ajp.dinein.data.datasource.SearchDataSource
import com.ajp.dinein.data.datasource.SearchDataSourceImpl
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dsModule = module {
	factory<SearchDataSource> {
		SearchDataSourceImpl(gson = get(), context = androidApplication())
	}
	
	single {
		Gson()
	}
}