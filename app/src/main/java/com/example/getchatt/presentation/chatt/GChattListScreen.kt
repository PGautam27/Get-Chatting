package com.example.getchatt.presentation.chatt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getchatt.ui.theme.RoyalBlue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GChattListScreen() {

    var li = listOf("Gautam","Pritam","Samuel","Niranjan","Karthik","Ajay","Sonal","Srinidhi")
    val verticalScroll = rememberScrollState()
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier
                .background(Color.Transparent)
                .width(LocalConfiguration.current.screenWidthDp.dp - 20.dp)
                .height(30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

            }
        }
    }) {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(verticalScroll),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        li.forEachIndexed { index, s ->
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp - 20.dp)
                    .height(
                        LocalConfiguration.current.screenHeightDp.dp / 12
                    )
                    .clip(RoundedCornerShape(20.dp)), backgroundColor = RoyalBlue,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = s,
                        style = TextStyle(color = Color.Black, fontSize = 25.sp),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}