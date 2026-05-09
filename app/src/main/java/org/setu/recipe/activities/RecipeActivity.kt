package org.setu.recipe.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.setu.recipe.R
import org.setu.recipe.databinding.ActivityRecipeBinding
import org.setu.recipe.helpers.showImagePicker
import org.setu.recipe.main.MainApp
import org.setu.recipe.models.RecipeModel
import timber.log.Timber
import timber.log.Timber.i

class RecipeActivity : AppCompatActivity() {

    var edit = false
    private lateinit var binding: ActivityRecipeBinding

    var recipe = RecipeModel()
    val ingredientsList = arrayListOf<String>()

    lateinit var app: MainApp

    private lateinit var imageIntentLauncher : ActivityResultLauncher<PickVisualMediaRequest>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = "RecipEz"
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Recipe Activity started...")

        if (intent.hasExtra("recipe_edit")) {
            edit = true
            recipe = intent.extras?.getParcelable("recipe_edit")!!
            binding.recipeTitle.setText(recipe.title)
            binding.description.setText(recipe.description)
            ingredientsList.addAll(recipe.ingredients)
            binding.ingredientsDisplay.text = ingredientsList.joinToString("\n")
            binding.calories.setText(recipe.calories.toString())
            binding.btnAdd.setText(R.string.save_recipe)
            Picasso.get()
                .load(recipe.image)
                .into(binding.recipeImage)
            if (recipe.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_recipe_image)
            }
        }

        binding.btnAddIngredient.setOnClickListener {
            val ingredient = binding.ingredients.text.toString()

            if (ingredient.isNotEmpty()) {
                ingredientsList.add(ingredient)
                binding.ingredientsDisplay.text = ingredientsList.joinToString("\n")
                binding.ingredients.text.clear()
            }
        }

        binding.btnAdd.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.description.text.toString()
            recipe.ingredients = ingredientsList
            recipe.calories = binding.calories.text.toString().toInt()

            if (recipe.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_recipe_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.recipes.update(recipe.copy())
                } else {
                    app.recipes.create(recipe.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }
        binding.chooseImage.setOnClickListener {
            i("Select image")
        }

        binding.chooseImage.setOnClickListener {
            val request = PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                .build()
            imageIntentLauncher.launch(request)
        }

        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (edit) menu.findItem(R.id.item_delete).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                setResult(99)
                app.recipes.delete(recipe)
                finish()
            }
            R.id.item_cancel -> {  finish()  }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher = registerForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) {
            try{
                contentResolver
                    .takePersistableUriPermission(it!!,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION )
                recipe.image = it // The returned Uri
                i("IMG :: ${recipe.image}")
                Picasso.get()
                    .load(recipe.image)
                    .into(binding.recipeImage)
            }
            catch(e:Exception){
                e.printStackTrace()
            }
        }
    }

}