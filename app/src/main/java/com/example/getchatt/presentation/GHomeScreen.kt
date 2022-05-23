package com.example.getchatt.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.getchatt.R
import com.example.getchatt.ui.theme.RoyalBlue
import com.example.getchatt.ui.theme.White

@Composable
fun GHomeScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gchatticon),
            contentDescription = null,
            modifier = Modifier
                .height(
                    LocalConfiguration.current.screenHeightDp.dp / 2
                )
                .width(LocalConfiguration.current.screenWidthDp.dp - 40.dp)
        )
        Row( modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 9)
            .background(color = White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Let's Get Started",
                color = RoyalBlue,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = LocalConfiguration.current.fontScale.times(25).sp
                )
            )
        }
        ChattingGuys()
    }
}

@Composable
private fun ChattingGuys () {

    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.chatting

        )
    )

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )

    LottieAnimation(compositionResult.value, progress)

}