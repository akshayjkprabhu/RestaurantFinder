package com.ajp.dinein.domain.usecase

import com.ajp.dinein.domain.model.Error


sealed class UseCaseResult<out T> {
	
	class Success<out T>(val data : T) : UseCaseResult<T>()
	
	class Failure(val exception : Error) : UseCaseResult<Nothing>()
}