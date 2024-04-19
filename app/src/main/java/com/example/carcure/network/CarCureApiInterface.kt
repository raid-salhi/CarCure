package com.example.carcure.network

import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import com.example.carcure.model.Signs
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface CarCureApiInterface {
    @GET("api/signs")
    fun getSigns (): Call<List<Sign>>
    @POST("api/diagnosis/")
    fun getDiagnosis(@Body signs: Signs): Call<Problem>
}