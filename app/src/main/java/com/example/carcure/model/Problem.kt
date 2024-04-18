package com.example.carcure.model

data class Problem(
    val id:Int,
    val name:String,
    val description:String,
    val solutions:String,
    val signs:List<Sign>
)
