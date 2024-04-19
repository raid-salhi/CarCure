package com.example.carcure.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.carcure.R
import com.example.carcure.model.Sign
import com.example.carcure.naviagation.Screens
import com.example.carcure.ui.theme.Background
import com.example.carcure.ui.theme.MyBlack
import com.example.carcure.ui.theme.MyBlue
import com.example.carcure.ui.theme.MyGrey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun MainScreen(navController: NavHostController,mainViewModel: MainViewModel) {
//    var availableTags by remember{
//        mutableStateOf(listOf("OverHeating","Vibrations","Brake Problems","Engine Misfiring","Noises","Decreased Fuel Efficiency"))
//    }
    var availableTags by remember{
        mutableStateOf(emptyList<Sign>())
    }
    var tags by remember{
        mutableStateOf(listOf<Sign>())
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
                onAction = {navController.navigate(Screens.ResultScreen.name)}
            ){ tag ->
                tags = tags - tag
                availableTags = availableTags+tag


            }
            Tags(tags =availableTags, { tag ->
                availableTags = availableTags-tag
                tags = tags + tag
                Log.d("TAG", "MainScreen:$tags ")
            }, Modifier)
        }
    }
}
@Composable
fun TagsContainer(tags: List<Sign>, onAction:()->Unit, onClick: (tag:Sign) -> Unit) {
    Card (
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp,color= Color(0xff94A1B2)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = MyGrey),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
            .padding(10.dp)){
            if (tags.isEmpty())
                Text(
                    text = "Select one or many tag",
                    color =Color(0xff94A1B2) ,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium))),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                )
            else
                Box(modifier = Modifier.fillMaxWidth()){
                    Tags(modifier =Modifier.align(Alignment.CenterStart).padding(end = 32.dp), tags = tags, onClick =onClick)
                    IconButton(
                        onClick = { onAction.invoke() },
                        modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.next),
                            contentDescription ="next",
                            modifier = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp),
                            tint = MyBlue
                        )
                    }
                }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(tags: List<Sign>, onClick: (tag: Sign) -> Unit, modifier: Modifier) {
    FlowRow(
        modifier=modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Center
    ) {
        for (tag in tags){
            TagHolder(tag,onClick)
        }
    }

}

@Composable
fun TagHolder(tag: Sign , onClick: (tag:Sign) -> Unit) {
    Box(modifier = Modifier
        .padding(end = 5.dp, top = 3.dp, bottom = 3.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(MyBlack)
        .clickable { onClick(tag) }
    ){
        Text(
            text = tag.name,
//            fontFamily = FontFamily(listOf((Font(R.font.roboto_medium)))),
            fontSize = 16.sp,
            color= Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 5.dp, horizontal = 15.dp)
        )
    }
}
