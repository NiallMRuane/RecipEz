package org.setu.recipe.models

data class RecipeModel(var title: String ="",
                       var description: String="",
                       var ingredient1: String="",
                       var ingredient2: String="",
                       var ingredient3: String="",
                       var calories: Int=0)
