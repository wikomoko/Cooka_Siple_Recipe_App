package com.example.cooka.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooka.DetailActivity
import com.example.cooka.databinding.ListItemsBinding
import com.example.cooka.model.RecipeCollections
import com.example.cooka.model.recipeContains
import com.squareup.picasso.Picasso

class FoodAdapter(val list:ArrayList<RecipeCollections>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    inner class FoodViewHolder(val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(recipeCollections: RecipeCollections){
            with(binding){
                Picasso.get().load(recipeCollections.recipe.image).into(listImage)
                listFoodName.text = recipeCollections.recipe.label
                listFoodSource.text = recipeCollections.recipe.source
                cardPadder.setOnClickListener {
                    val infos = recipeContains(
                        recipeCollections.recipe.uri,
                        recipeCollections.recipe.label,
                        recipeCollections.recipe.image,
                        recipeCollections.recipe.source,
                        recipeCollections.recipe.ingredients
                    )

                    val move = Intent(this.cardPadder.context,DetailActivity::class.java)
                        .putExtra(DetailActivity.data_reeciever,infos)
                    this.cardPadder.context.startActivity(move)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val v = ListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}