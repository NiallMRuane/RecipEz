package org.setu.recipe.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(var id: Long = 0,
                       var title: String ="",
                       var description: String="",
                       var ingredient1: String="",
                       var ingredient2: String="",
                       var ingredient3: String="",
                       var calories: Int=0) : Parcelable
