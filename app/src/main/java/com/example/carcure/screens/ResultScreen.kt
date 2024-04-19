package com.example.carcure.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.carcure.R
import com.example.carcure.screens.componants.ResultCard
import com.example.carcure.screens.viewmodels.SharedViewModel
import com.example.carcure.ui.theme.Background
import com.example.carcure.ui.theme.MyBlack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavHostController,sharedViewModel: SharedViewModel) {
    val problem = sharedViewModel.myProblem!!
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
            ResultCard(problem=problem)
        }
    }
}
