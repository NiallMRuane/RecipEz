package org.setu.recipe.main

import android.app.Application
import org.setu.recipe.models.RecipeJSONStore
import org.setu.recipe.models.RecipeMemStore
import org.setu.recipe.models.RecipeModel
import org.setu.recipe.models.RecipeStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var recipes: RecipeStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        recipes = RecipeJSONStore(applicationContext)
        //recipes = RecipeMemStore()
        i("Recipe started")
    }
}