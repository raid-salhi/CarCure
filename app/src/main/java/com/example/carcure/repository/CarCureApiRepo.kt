package com.example.carcure.repository

import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import com.example.carcure.model.Signs
import com.example.carcure.network.CarCureApiInterface
import retrofit2.Call
import javax.inject.Inject

class CarCureApiRepo @Inject constructor(private val apiInterface: CarCureApiInterface) {
    fun getSigns(): Call<List<Sign>> = apiInterface.getSigns()
    fun getDiagnosis(signs: Signs): Call<Problem> = apiInterface.getDiagnosis(signs)
}