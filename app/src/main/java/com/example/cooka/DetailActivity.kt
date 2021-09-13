package com.example.cooka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooka.adapter.FlavourAdapter
import com.example.cooka.databinding.ActivityDetailBinding
import com.example.cooka.model.Ingredients
import com.example.cooka.model.recipeContains
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private val list = ArrayList<Ingredients>()

    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val getDatas = intent.getParcelableExtra<recipeContains>(data_reeciever) as recipeContains

        binding.rvFlavour.setHasFixedSize(true)
        binding.rvFlavour.layoutManager = LinearLayoutManager(this)

        Picasso.get().load(getDatas.image).into(binding.foodImageDetail)
        binding.foodNameDetail.text = getDatas.label
        binding.foodeMakerDetail.text = getDatas.source

        list.addAll(getDatas.ingredients)
        binding.rvFlavour.adapter = FlavourAdapter(list)

    }

    companion object{
        val data_reeciever = "data_input"
    }
}