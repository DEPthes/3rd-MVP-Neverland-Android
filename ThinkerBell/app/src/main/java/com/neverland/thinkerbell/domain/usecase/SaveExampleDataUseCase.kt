package com.neverland.thinkerbell.domain.usecase

import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SaveExampleDataUseCase() {
    private val repository: DataStoreRepositoryImpl = DataStoreRepositoryImpl()

    suspend operator fun invoke(value: String) {
        withContext(Dispatchers.IO) {
            repository.saveExampleData(value)
        }
    }
}