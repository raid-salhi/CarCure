package com.example.carcure.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.carcure.R
import com.example.carcure.naviagation.Screens
import com.example.carcure.ui.theme.MyBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(500)
        navController.navigate(Screens.MainScreen.name)
//        navController.popBackStack()
    }
   Surface(
       modifier = Modifier.fillMaxSize(),
       color = MyBlack
   ) {
       Column (
           modifier=Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           Image(
               painter = painterResource(id = R.drawable.logo),
               contentDescription ="Logo",
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth(0.5f)
           )
           Text(
               text ="CarCure" ,
               color = Color.White,
               fontFamily = FontFamily(listOf(Font(R.font.kanit_medium_italic))),
               fontSize = 40.sp,
               modifier = Modifier.padding(top = 10.dp)
           )
       }
   }
}