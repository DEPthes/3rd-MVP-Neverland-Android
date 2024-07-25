package com.neverland.thinkerbell.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun clearData(): Result<Boolean>

    suspend fun saveExampleData(value: String): Result<Boolean>

    fun readExampleData(): Flow<String?>
}