package com.example.getchatt.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.*
import com.example.getchatt.R

@Composable
fun GRegistrationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

    }
}

//@Composable
//private fun ChattingGroup () {
//
//    val compositionResult: LottieCompositionResult = rememberLottieComposition(
//        spec = LottieCompositionSpec.RawRes(
//            R.raw.groupchatting
//
//        )
//    )
//
//    val progress by animateLottieCompositionAsState(
//        compositionResult.value,
//        isPlaying = true,
//        iterations = LottieConstants.IterateForever,
//        speed = 0.9f
//    )
//
//    LottieAnimation(compositionResult.value, progress)
//
//}