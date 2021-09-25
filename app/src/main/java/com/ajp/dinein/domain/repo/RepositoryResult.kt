package com.ajp.dinein.domain.repo

import com.ajp.dinein.domain.model.Error

sealed class RepositoryResult<out T> {
	
	class Success<out T>(val repositoryData : T) : RepositoryResult<T>()
	
	class Failure(val error : Error) : RepositoryResult<Nothing>()
}