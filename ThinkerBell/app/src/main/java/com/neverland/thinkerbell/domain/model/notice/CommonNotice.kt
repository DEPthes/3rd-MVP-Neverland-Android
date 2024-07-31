package com.neverland.thinkerbell.domain.model.notice

data class CommonNotice(
    val title: String,
    val date: String,
    val url: String,
    val isImportant: Boolean?,
    val classification: String?
)