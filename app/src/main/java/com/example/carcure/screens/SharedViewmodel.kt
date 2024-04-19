package com.example.carcure.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.carcure.model.Problem

class SharedViewModel(): ViewModel() {
    var myProblem by mutableStateOf<Problem?>(null)
        private set
    fun addProblem(newProblem: Problem){
        myProblem = newProblem
    }

}