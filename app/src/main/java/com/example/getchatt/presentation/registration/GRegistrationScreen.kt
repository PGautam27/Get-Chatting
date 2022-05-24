package com.example.getchatt.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    BottomSheetScaffold(sheetContent = { register() }, content = { mainContent()}, sheetShape = (if (sheetState.isAnimationRunning || sheetState.isVisible){
        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
    }else{
        RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
    }) as Shape,
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) 0.dp else 200.dp
    )
}

@Composable
private fun mainContent() {
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
    }
}

@Composable
private fun register() {
    val emailValue = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(RoyalBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter you email and \n set a password", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(25).sp))
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/10))
        OutlinedTextField(value = emailValue.value, onValueChange = {emailValue.value = it})
    }
}