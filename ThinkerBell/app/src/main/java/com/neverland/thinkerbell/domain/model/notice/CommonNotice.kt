package com.neverland.thinkerbell.domain.model.notice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommonNotice(
    val title: String,
    val date: String,
    val url: String,
    val isImportant: Boolean?,
    val classification: String?
) : Parcelable