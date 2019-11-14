package com.example.testsharedelementtransition

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemModel (
    val id: Int,
    val image: String,
    val title: String,
    val name: String
): Parcelable