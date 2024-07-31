package com.neverland.thinkerbell.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notice(
    val title: String,
    val date: String
) : Parcelable
