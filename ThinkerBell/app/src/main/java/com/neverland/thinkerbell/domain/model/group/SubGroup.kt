package com.neverland.thinkerbell.domain.model.group

data class SubGroup<T>(
    val name: String,
    val items: List<T>,
    var isExpanded: Boolean = false
)