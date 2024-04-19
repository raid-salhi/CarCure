package com.example.carcure.screens.componants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.carcure.R
import com.example.carcure.model.Problem
import com.example.carcure.model.Sign
import com.example.carcure.ui.theme.MyBlack
import com.example.carcure.ui.theme.MyBlue
import com.example.carcure.ui.theme.MyGrey

@Composable
fun TagsContainer(tags: List<Sign>, onAction:()->Unit, onClick: (tag: Sign) -> Unit) {
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
                    color = Color(0xff94A1B2) ,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium))),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                )
            else
                Box(modifier = Modifier.fillMaxWidth()){
                    Tags(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(end = 32.dp), tags = tags, onClick =onClick)
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
fun TagHolder(tag: Sign, onClick: (tag: Sign) -> Unit) {
    Box(modifier = Modifier
        .padding(end = 5.dp, top = 3.dp, bottom = 3.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(MyBlack)
        .clickable { onClick(tag) }
    ){
        Text(
            text = tag.name,
            fontSize = 16.sp,
            color= Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 5.dp, horizontal = 15.dp)
        )
    }
}
@Composable
fun ResultCard(problem: Problem) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, MyGrey),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = problem.name,
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
            text = problem.description,
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
        val solutions = problem.solutions.split(',').map { it.capitalize() }
        for (index in solutions.indices)
            Text(
                text = (index+1).toString() +". "+ solutions[index] ,
                fontFamily = FontFamily(listOf(Font(R.font.roboto))),
                fontSize = 16.sp,
                color = MyBlack,
                modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
            )
        Tags(tags = problem.signs, onClick = {}, modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp))

    }
}