package com.example.getchatt.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getchatt.R
import com.example.getchatt.ui.theme.RoyalBlue
import com.example.getchatt.ui.theme.White

@ExperimentalMaterialApi
@Composable
fun GRegistrationScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomSheetScaffold(sheetContent = { register() }, content = { Images()}, sheetShape = (if (sheetState.isAnimationRunning || sheetState.isVisible){
        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
    }else{
        RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
    }) as Shape,
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) 0.dp else 180.dp
    )
}

@Composable
private fun Images() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        //verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        contentAlignment = Alignment.TopCenter
    ){
        Image(
            painter = painterResource(id = R.drawable.gettochatt),
            contentDescription = null,
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp / 1.8f)
                .fillMaxWidth()
        )  
        Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp/50))

    }
}

@Composable
private fun register() {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 2 + 50.dp)
            .background(RoyalBlue),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Let's Get you Registered", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(28).sp), fontWeight = FontWeight.Bold)
        //Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/40))
        Text(text = "Enter your email and \n set a password", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(25).sp))
        //Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/40))
        OutlinedTextField(
            value = emailValue.value,
            placeholder = { Text(text = "xyz@gmail.com", style = TextStyle(fontSize = 16.sp, color = White))},
            onValueChange = { emailValue.value = it },
            label = { Text(text = "EMAIL ID", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = LocalConfiguration.current.fontScale.times(18).sp))},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White,
                textColor = Color.White,
                leadingIconColor = Color.White
            ),
            textStyle = TextStyle( fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp - 80.dp)
        )
        OutlinedTextField(
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            label = { Text(text = "PASSWORD", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = LocalConfiguration.current.fontScale.times(18).sp))},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White,
                textColor = Color.White,
                leadingIconColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle( fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp - 80.dp)
        )
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = White,
        ), elevation = ButtonDefaults.elevation(0.dp), modifier = Modifier
            .height(50.dp)
            .width(150.dp)
            .border(2.dp, shape = RoundedCornerShape(20.dp), color = Color.White) ) {
            Text(text = "REGISTER", fontSize = 16.sp)
        }
    }
}