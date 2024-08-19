package com.neverland.thinkerbell.domain.model

data class PageableNotice<T>(
    val totalItems: Int,
    val size: Int,
    val page: Int,
    val items: List<T>
)
