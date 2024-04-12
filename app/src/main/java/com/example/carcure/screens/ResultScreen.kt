package com.example.carcure.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.carcure.R
import com.example.carcure.ui.theme.Background
import com.example.carcure.ui.theme.MyBlack
import com.example.carcure.ui.theme.MyBlue
import com.example.carcure.ui.theme.MyGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack()}) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription ="back",
                        modifier = Modifier.size(26.dp),
                        tint = MyBlack
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
        )},
        containerColor = Background
    ){
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding() + 20.dp,
                    bottom = 20.dp,
                    start = 15.dp,
                    end = 15.dp
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
            ) {
            Text(
                text = "We’ve got a match !",
                fontFamily = FontFamily(listOf(Font(R.font.montserrat_semi_bold))),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )
            ResultCard()
        }
    }
}
@Composable
fun ResultCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MyGrey),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Worn brake pads",
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semi_bold))),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = MyBlack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 20.dp)
        )
        Text(
            text = "Description :",
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semi_bold))),
            fontSize = 18.sp,
            color = MyBlue,
            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.cause),
            fontFamily = FontFamily(listOf(Font(R.font.roboto))),
            fontSize = 16.sp,
            color = MyBlack,
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
        )
        Text(
            text = "Solutions :",
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semi_bold))),
            fontSize = 18.sp,
            color = MyBlue,
            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
        )
        val solutions = listOf("your brake pads need to be replaced","address this issue promptly to prevent further damage to the brake system","Bring your car to a qualified mechanic or brake specialisBring your car to a qualified mechanic or brake specialist")
        for (index in solutions.indices)
            Text(
                text = (index+1).toString() +". "+ solutions[index] ,
                fontFamily = FontFamily(listOf(Font(R.font.roboto))),
                fontSize = 16.sp,
                color = MyBlack,
                modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
            )
        Tags(tags = listOf("Brake Problems","Noises"), onClick = {}, modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp))

    }
}