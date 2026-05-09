package org.setu.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.setu.recipe.databinding.CardRecipeBinding
import org.setu.recipe.models.RecipeModel

interface RecipeListener {
    fun onRecipeClick(recipe: RecipeModel, position: Int)
}

class RecipeAdapter(private var recipes: List<RecipeModel>,
                    private val listener: RecipeListener) :
    RecyclerView.Adapter<RecipeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding : CardRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel, listener: RecipeListener) {
            binding.recipeTitle.text = recipe.title
            binding.description.text = recipe.description
            binding.ingredients.text = recipe.ingredients.joinToString("\n")
            binding.calories.text = recipe.calories.toString()
            Picasso.get().load(recipe.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onRecipeClick(recipe, adapterPosition) }
        }
    }
}