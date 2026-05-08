package org.setu.recipe.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.setu.recipe.R
import org.setu.recipe.databinding.ActivityRecipeBinding
import org.setu.recipe.main.MainApp
import org.setu.recipe.models.RecipeModel
import timber.log.Timber.i

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var recipe = RecipeModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = "RecipEz"
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        i("Recipe Activity started...")

        if (intent.hasExtra("recipe_edit")) {
            recipe = intent.extras?.getParcelable("recipe_edit")!!
            binding.recipeTitle.setText(recipe.title)
            binding.description.setText(recipe.description)
            binding.ingredient1.setText(recipe.ingredient1)
            binding.ingredient2.setText(recipe.ingredient2)
            binding.ingredient3.setText(recipe.ingredient3)
            binding.calories.setText(recipe.calories)
        }

        binding.btnAdd.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.description.text.toString()
            recipe.ingredient1 = binding.ingredient1.text.toString()
            recipe.ingredient2 = binding.ingredient2.text.toString()
            recipe.ingredient3 = binding.ingredient3.text.toString()
            recipe.calories = binding.calories.text.toString().toInt()

            if (recipe.title.isNotEmpty()) {
                app.recipes.create(recipe.copy())
                i("add Button Pressed: $recipe")
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}