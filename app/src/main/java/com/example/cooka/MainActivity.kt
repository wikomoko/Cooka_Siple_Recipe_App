package com.example.cooka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooka.adapter.FoodAdapter
import com.example.cooka.databinding.ActivityMainBinding
import com.example.cooka.model.RecipeCollections
import com.example.cooka.model.RecipeDatas
import com.example.cooka.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<RecipeCollections>()
    lateinit var searchingFood: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)
        searchingFood = "salmon"
        showDataFromApi()
        binding.btnSearch.setOnClickListener(this)
    }

    private fun showDataFromApi() {
        if(searchingFood.isNullOrEmpty()) searchingFood = "chicken"
        RetrofitClient.instanse.getDatas("public", "$searchingFood",).enqueue(object : Callback<RecipeDatas> {
            override fun onResponse(call: Call<RecipeDatas>, response: Response<RecipeDatas>) {
                val responses = response.body()?.hits
                Log.i("eoa", responses.toString())
                responses?.let { list.addAll(it) }
                val adapter = FoodAdapter(list)
                binding.rvPost.adapter = adapter
                if (responses.toString() == "[]") Toast.makeText(this@MainActivity, "kosong", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
                binding.foodSearch.text = null
            }
            override fun onFailure(call: Call<RecipeDatas>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onClick(v: View?) {
        val inputForSearch = binding.foodSearch.text.toString()
        list.clear()
        binding.progressBar.visibility = View.VISIBLE
        searchingFood = inputForSearch
        showDataFromApi()
    }
}