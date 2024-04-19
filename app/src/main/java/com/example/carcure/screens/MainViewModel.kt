package com.example.carcure.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import com.example.carcure.model.Signs
import com.example.carcure.repository.CarCureApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CarCureApiRepo): ViewModel() {

    fun getDiagnosis(signs: Signs): Call<Problem> = repo.getDiagnosis(signs)
    fun getSigns(): Call<List<Sign>> = repo.getSigns()
}