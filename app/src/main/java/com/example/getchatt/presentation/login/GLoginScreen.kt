package com.example.getchatt.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getchatt.R
import com.example.getchatt.ui.theme.RoyalBlue

@Composable
fun GLoginScreen() {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    val passwordHide = remember {
        mutableStateOf(true)
    }
    val painterValue = remember {
        mutableStateOf(R.drawable.visibile)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = AnnotatedString(
                "      Sign in\n",
                spanStyle = SpanStyle(
                    color = Color.White,
                    fontSize = LocalConfiguration.current.fontScale.times(35).sp,
                    fontWeight = FontWeight.Bold
                )
            ).plus(
                AnnotatedString(
                    "Enter your credentials to\n \t\t\t\t\t\t\t\t\t\t\t\tcontinue",
                    spanStyle = SpanStyle(
                        color = Color.White,
                        fontSize = LocalConfiguration.current.fontScale.times(20).sp,
                    ),
                )
            ),
        )
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/40))
        Divider(
            modifier = Modifier
                .height(3.dp)
                .width(LocalConfiguration.current.screenWidthDp.dp - 160.dp)
                .background(
                    RoyalBlue
                )
        )
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/40))
        OutlinedTextField(
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            label = { Text(text = "EMAIL ID", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = LocalConfiguration.current.fontScale.times(18).sp))},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = RoyalBlue,
                unfocusedBorderColor = RoyalBlue,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                textColor = Color.White
            ),
            textStyle = TextStyle( fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 60.dp)
                .height(LocalConfiguration.current.screenHeightDp.dp / 11),
            shape = RoundedCornerShape(30.dp)
        )
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/50))
        OutlinedTextField(
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            label = { Text(text = "Password", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = LocalConfiguration.current.fontScale.times(18).sp))},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = RoyalBlue,
                unfocusedBorderColor = RoyalBlue,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                textColor = Color.White
            ),
            visualTransformation = if (passwordHide.value) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 60.dp)
                .height(LocalConfiguration.current.screenHeightDp.dp / 11),
            shape = RoundedCornerShape(30.dp),
            maxLines = 1,
            textStyle = TextStyle(fontSize = if (passwordHide.value) 28.sp else 18.sp),
            trailingIcon = { Image(painter = painterResource(id = painterValue.value), contentDescription = null, modifier = Modifier
                .clickable {
                    passwordHide.value = !passwordHide.value
                    if (passwordHide.value)
                        painterValue.value = R.drawable.notvisible
                    else
                        painterValue.value = R.drawable.visibile
                }
                .size(28.dp))}
        )
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/48))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RoyalBlue
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .width(LocalConfiguration.current.screenWidthDp.dp - 60.dp)
                .height(
                    LocalConfiguration.current.screenHeightDp.dp / 11
                )
        ) {
            Text(
                text = "SIGN IN",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}