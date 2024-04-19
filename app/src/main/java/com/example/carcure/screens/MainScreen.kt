package com.example.carcure.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.carcure.R
import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import com.example.carcure.model.Signs
import com.example.carcure.naviagation.Screens
import com.example.carcure.screens.componants.Tags
import com.example.carcure.screens.componants.TagsContainer
import com.example.carcure.screens.viewmodels.SharedViewModel
import com.example.carcure.ui.theme.Background
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel(), sharedViewModel: SharedViewModel) {

    var availableTags by remember{
        mutableStateOf(emptyList<Sign>())
    }
    var tags by remember{
        mutableStateOf(listOf<Sign>())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = 1) {
        val signsList= mainViewModel.getSigns()
        signsList.enqueue(object : Callback<List<Sign>> {
            override fun onResponse(call: Call<List<Sign>>, response: Response<List<Sign>>) {
                if (response.isSuccessful){
                    Log.d("TAG", "onResponse: ${response.body()!!.size}")
                    availableTags=response.body()!!
                    call.cancel()
                }
                else{
                    Log.d("TAG", "onResponse failed: ${response.errorBody().toString()} ")
                    call.cancel()
                }
            }
            override fun onFailure(call: Call<List<Sign>>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.localizedMessage}  ")
            }
        })
    }
    Surface (color = Background, modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 75.dp, start = 25.dp, end = 25.dp)) {
            Text(
                text = "Let’s see what’s \n" + "wrong with your car !",
                fontFamily = FontFamily(listOf(Font(R.font.montserrat_semi_bold))),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )
            TagsContainer(
                tags = tags,
                onAction = {
                    isLoading = true
                    val problem= mainViewModel.getDiagnosis(Signs(signs = tags.map { it.name }))
                    problem.enqueue(object : Callback<Problem> {
                        override fun onResponse(call: Call<Problem>, response: Response<Problem>) {
                            if (response.isSuccessful){
                                Log.d("TAG", "onResponse: ${response.body()!!.id}")
                                isLoading = false
                                sharedViewModel.addProblem(response.body()!!)
                                navController.navigate(Screens.ResultScreen.name)
                                call.cancel()
                            }
                            else{
                                Log.d("TAG", "onResponse failed: ${response.errorBody().toString()} ")
                                call.cancel()
                            }
                        }
                        override fun onFailure(call: Call<Problem>, t: Throwable) {
                            Log.d("TAG", "onFailure: ${t.localizedMessage}  ")
                        }
                    })

                }
            ){ tag ->
                tags = tags - tag
                availableTags = availableTags+tag
            }
            Tags(
                tags =availableTags,
                onClick = { tag ->
                availableTags = availableTags-tag
                tags = tags + tag
                Log.d("TAG", "MainScreen:$tags ")
            },
                modifier = Modifier
            )
        }
    }
}
