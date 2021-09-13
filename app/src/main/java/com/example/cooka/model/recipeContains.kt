package com.example.cooka.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class recipeContains (
    val uri:String,
    val label : String,
    val image : String,
    val source : String,
    val ingredients : ArrayList<Ingredients>
    ):Parcelable
