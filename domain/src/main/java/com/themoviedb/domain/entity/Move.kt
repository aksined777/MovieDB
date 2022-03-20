package com.themoviedb.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Move(
    var id:Long = 0,
    val name: String = "",
    val rating: Double = 0.0,
    val script: String = "",
    val poster: String,
    val timeHistory: Long = 0,
) : Parcelable

