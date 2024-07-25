package com.neverland.thinkerbell.domain.usecase

import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers

class GetExampleDataUseCase {
    private val repository: DataStoreRepositoryImpl = DataStoreRepositoryImpl()

    operator fun invoke(): Flow<String?> {
        return repository.readExampleData().flowOn(Dispatchers.IO)
    }
}
