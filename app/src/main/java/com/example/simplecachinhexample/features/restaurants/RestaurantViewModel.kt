package com.example.simplecachinhexample.features.restaurants

import androidx.lifecycle.*
import com.example.simplecachinhexample.api.RestaurantApi
import com.example.simplecachinhexample.data.Restaurant
import com.example.simplecachinhexample.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    repository: RestaurantRepository
) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()

}