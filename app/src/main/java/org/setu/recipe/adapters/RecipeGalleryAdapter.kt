package org.setu.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.setu.recipe.databinding.CardRecipeGalleryBinding
import org.setu.recipe.models.RecipeModel

class RecipeGalleryAdapter(private var recipes: List<RecipeModel>) :
    RecyclerView.Adapter<RecipeGalleryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeGalleryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding: CardRecipeGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            Picasso.get()
                .load(recipe.image)
                .into(binding.galleryImage)
        }
    }
}