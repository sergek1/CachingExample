package com.example.simplecachinhexample.di

import android.app.Application
import androidx.room.Room
import com.example.simplecachinhexample.api.RestaurantApi
import com.example.simplecachinhexample.data.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(RestaurantApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "restaurant_database").build()
}