package org.setu.recipe.activities

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.setu.recipe.adapters.RecipeGalleryAdapter
import org.setu.recipe.databinding.ActivityRecipeGalleryBinding
import org.setu.recipe.databinding.ContentRecipeGalleryBinding
import org.setu.recipe.main.MainApp

class RecipeGalleryActivity : AppCompatActivity() {

    lateinit var app: MainApp

    private lateinit var binding: ActivityRecipeGalleryBinding
    private lateinit var contentBinding: ContentRecipeGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        app = application as MainApp

        binding = ActivityRecipeGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        contentBinding = ContentRecipeGalleryBinding.bind(binding.root)

        val layoutManager = LinearLayoutManager(this)
        contentBinding.galleryRecyclerView.layoutManager = layoutManager
        contentBinding.galleryRecyclerView.adapter = RecipeGalleryAdapter(app.recipes.findAll())

    }

}
