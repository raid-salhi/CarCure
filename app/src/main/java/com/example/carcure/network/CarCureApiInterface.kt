package com.example.carcure.network

import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface CarCureApiInterface {
    @GET("api/signs")
    suspend fun getSigns ():List<Sign>
    @POST("api/diagnosis")
    fun getDiagnosis(@Body signs:List<Sign>): Call<Problem>
}