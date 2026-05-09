package org.setu.recipe.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(var id: Long = 0,
                       var title: String ="",
                       var description: String="",
                       var ingredients: ArrayList<String> = arrayListOf(),
                       var calories: Int=0,
                       var image: Uri = Uri.EMPTY) : Parcelable

