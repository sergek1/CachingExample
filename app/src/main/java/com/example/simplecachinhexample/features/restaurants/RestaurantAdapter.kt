package com.example.simplecachinhexample.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplecachinhexample.R
import com.example.simplecachinhexample.data.Restaurant
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantAdapterViewHolder>() {

    private var restaurantList = ArrayList<Restaurant>()

    fun setRestaurantList(restaurantList: List<Restaurant>) {
        this.restaurantList = restaurantList as ArrayList<Restaurant>
        notifyDataSetChanged()
    }


    inner class RestaurantAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantAdapterViewHolder {
        return RestaurantAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.restaurant_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantAdapterViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.itemView.apply {
            tv_name.text = restaurant.name
            tv_type.text = restaurant.type
            tv_address.text = restaurant.address
            Glide.with(this).load(restaurant.logo).into(iv_logo)
        }
    }
}