package com.ajp.dinein.core.di

import com.ajp.dinein.domain.usecase.search.SearchUseCase
import com.ajp.dinein.domain.usecase.search.SearchUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<SearchUseCase> {
		SearchUseCaseImpl(get())
	}
}