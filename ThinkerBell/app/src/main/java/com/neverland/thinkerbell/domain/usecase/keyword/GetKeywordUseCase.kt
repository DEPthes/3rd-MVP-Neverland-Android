package com.neverland.thinkerbell.domain.usecase.keyword

import com.neverland.thinkerbell.data.repository.KeywordRepositoryImpl
import com.neverland.thinkerbell.domain.model.keyword.Keyword

class GetKeywordUseCase {
    private val repository: KeywordRepositoryImpl = KeywordRepositoryImpl()

    suspend operator fun invoke(ssaId: String): Result<List<Keyword>> {
        val data = repository.getKeyword(ssaId).getOrNull()
        return if (data != null){
            Result.success(data)
        } else {
            Result.failure(Exception())
        }
    }
}