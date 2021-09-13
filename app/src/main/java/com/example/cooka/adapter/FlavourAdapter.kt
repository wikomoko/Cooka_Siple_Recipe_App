package com.example.cooka.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooka.databinding.ListItemsSubsBinding
import com.example.cooka.model.Ingredients
import com.squareup.picasso.Picasso

class FlavourAdapter (val list: ArrayList<Ingredients>): RecyclerView.Adapter<FlavourAdapter.FalvourViewHolder>(){
    inner class FalvourViewHolder (val binding: ListItemsSubsBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredients: Ingredients){
            with(binding){
                flavorName.text = ingredients.text
                flavorWieght.text = "${ingredients.weight} gram"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FalvourViewHolder {
        val binding = ListItemsSubsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FalvourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FalvourViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}