package com.example.getchatt.presentation.chatt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getchatt.ui.theme.RoyalBlue

@Composable
fun GChatScreen() {
    val messageValue = remember {
        mutableStateOf(TextFieldValue())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = messageValue.value,
                onValueChange = { messageValue.value = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = RoyalBlue,
                    unfocusedBorderColor = RoyalBlue,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    textColor = Color.White
                ),
                textStyle = TextStyle( fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp - 100.dp)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 11),
                shape = RoundedCornerShape(30.dp)
            )
            Box(
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp / 4)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 11)
                    .clip(RoundedCornerShape(20.dp))
                    .background(RoyalBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "send", tint = Color.White, modifier = Modifier.size(15.dp))
            }
        }
    }
}