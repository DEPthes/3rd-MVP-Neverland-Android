package com.neverland.thinkerbell.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

//class NonFlowExampleUseCase(private val repository: NonFlowExampleRepositoryImpl) {
//    operator fun invoke(): Flow<String?> {
//        return repository.readExampleData().flowOn(Dispatchers.IO)
//    }
//}