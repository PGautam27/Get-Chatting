package com.example.getchatt.presentation

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.getchatt.R
import com.example.getchatt.ui.theme.Black
import com.example.getchatt.ui.theme.RoyalBlue

@Composable
fun GHomeScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 8),
            colors = ButtonDefaults.buttonColors(backgroundColor = RoyalBlue)
        ) {

        }
    }
}