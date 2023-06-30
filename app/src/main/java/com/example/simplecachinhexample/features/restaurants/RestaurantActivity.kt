package com.example.simplecachinhexample.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplecachinhexample.R
import com.example.simplecachinhexample.util.Resourse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    private val viewModel: RestaurantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurantAdapter = RestaurantAdapter()
        rv_restaurants.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(this@RestaurantActivity)
        }
        viewModel.restaurants.observe(this) { result ->
            result.data?.let { restaurantAdapter.setRestaurantList(it) }
            progress_bar.isVisible = result is Resourse.Loading && result.data.isNullOrEmpty()
            tv_error.isVisible = result is Resourse.Error && result.data.isNullOrEmpty()
            tv_error.text = result.error?.localizedMessage
        }
    }
}