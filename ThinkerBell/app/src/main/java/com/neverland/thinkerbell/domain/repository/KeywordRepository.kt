package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.keyword.Keyword

interface KeywordRepository {

    suspend fun getKeyword(ssaId: String): Result<List<Keyword>>

    suspend fun postKeyword(keyword: String, ssaId: String): Result<Boolean>

    suspend fun deleteKeyword(keyword: String, ssaId: String): Result<Boolean>
}