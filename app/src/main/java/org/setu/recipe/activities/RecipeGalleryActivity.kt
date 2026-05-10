package org.setu.recipe.activities

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.setu.recipe.R
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.item_add).isVisible = false
        menu.findItem(R.id.item_gallery).isVisible = false
        menu.findItem(R.id.item_delete).isVisible = false
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
