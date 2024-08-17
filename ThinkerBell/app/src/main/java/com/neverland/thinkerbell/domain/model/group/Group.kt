package com.neverland.thinkerbell.domain.model.group

data class Group<T>(
    val name: String,
    val subGroups: List<SubGroup<T>>,
    val items: List<T>? = null,
    var isExpanded: Boolean = false
)