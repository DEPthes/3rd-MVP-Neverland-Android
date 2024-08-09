package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.enums.NoticeType
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun clearData(): Result<Boolean>

    suspend fun saveExampleData(value: String): Result<Boolean>

    fun readExampleData(): Flow<String?>

    suspend fun saveRecentSearchWord(word: String): Result<Boolean>

    fun readRecentSearchWords(): Flow<List<String>>

    suspend fun deleteRecentSearchWord(word: String): Result<Boolean>

    suspend fun saveCategoryOrder(list: List<NoticeType>): Result<Boolean>

    fun readCategoryOrder(): Flow<List<NoticeType>>
}