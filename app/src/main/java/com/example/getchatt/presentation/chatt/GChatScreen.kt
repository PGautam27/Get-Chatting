package com.example.getchatt.presentation.chatt

import androidx.compose.foundation.ScrollState
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
        Column(modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp - 70.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Bottom) {
            repeat(66){
                Row(modifier = Modifier.align(Alignment.End)) {
                    Text(text = "JHIHIHIO", color = Color.White)
                }
            }
            repeat(3){
                recieve()
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
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
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .width(LocalConfiguration.current.screenWidthDp.dp - 83.dp)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                    .focusRequester(
                        FocusRequester()
                    ),
                shape = RoundedCornerShape(30.dp)
            )
            Box(
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp / 5)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                    .clip(RoundedCornerShape(30.dp))
                    .background(RoyalBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "send", tint = Color.White, modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Composable
private fun recieve() {
    Row(modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(Color.White).height(LocalConfiguration.current.screenHeightDp.dp/20), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "HELLO HOW are you", color = RoyalBlue, fontSize = 15.sp, modifier = Modifier.padding(start = 5.dp, end = 5.dp), fontWeight = FontWeight.Bold)
    }
}