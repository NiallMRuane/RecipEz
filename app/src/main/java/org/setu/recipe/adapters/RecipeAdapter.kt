package org.setu.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.setu.recipe.databinding.CardRecipeBinding
import org.setu.recipe.models.RecipeModel

class RecipeAdapter(private var recipes: MutableList<RecipeModel>) :
    RecyclerView.Adapter<RecipeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    class MainHolder(private val binding : CardRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            binding.recipeTitle.text = recipe.title
            binding.description.text = recipe.description
            binding.ingredient1.text = recipe.ingredient1
            binding.ingredient2.text = recipe.ingredient2
            binding.ingredient3.text = recipe.ingredient3
            binding.calories.text = recipe.calories.toString()
        }
    }
}