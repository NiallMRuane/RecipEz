package org.setu.recipe.models

interface RecipeStore {
    fun findAll(): List<RecipeModel>
    fun create(recipe: RecipeModel)

    fun update(recipe: RecipeModel)
}