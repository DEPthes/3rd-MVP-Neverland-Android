package com.neverland.thinkerbell.domain.model.group

data class Group<T>(
    val name: String,
    val subGroups: List<SubGroup<T>>,
    var isExpanded: Boolean = false
)