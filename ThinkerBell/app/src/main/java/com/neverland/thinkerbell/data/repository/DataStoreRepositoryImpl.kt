package com.neverland.thinkerbell.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.domain.repository.DataStoreRepository
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.base.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Data Layer에서는 Presentation Layer를 모르기 때문에 의존성 주입 없이 application을 가져오면 안되지만,
// Hilt 학습 전이기에 그냥 진행
// [DI-Hilt 스터디 참고]

class DataStoreRepositoryImpl : DataStoreRepository {

    companion object {
        private val EXAMPLE_KEY = stringPreferencesKey("example_key")
    }

    override suspend fun clearData(): Result<Boolean> {
        LoggerUtil.d("clearData 호출")

        return try {
            application.dataStore.edit { preferences ->
                preferences.clear()
            }
            Result.success(true)
        } catch (e: Exception){
            Result.success(false)
        }
    }


    override suspend fun saveExampleData(value: String): Result<Boolean> {
        LoggerUtil.d("saveExampleData 호출")

        return try {
            application.dataStore.edit { preferences ->
                preferences[EXAMPLE_KEY] = value
            }
            Result.success(true)
        } catch (e: Exception){
            Result.success(false)
        }
    }

    override fun readExampleData(): Flow<String?> {
        LoggerUtil.d("saveExampleData 호출")

        return application.dataStore.data
            .map { preferences ->
                preferences[EXAMPLE_KEY]
            }
    }
}