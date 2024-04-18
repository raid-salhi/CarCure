package com.example.carcure.di

import com.example.carcure.network.CarCureApiInterface
import com.example.carcure.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideCarCureApiInterface(): CarCureApiInterface = Retrofit.Builder()
        .baseUrl(Constants.CAR_CURE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CarCureApiInterface::class.java)
}