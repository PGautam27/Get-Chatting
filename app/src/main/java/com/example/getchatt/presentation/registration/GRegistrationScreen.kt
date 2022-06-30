package com.example.getchatt.presentation.registration

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.getchatt.R
import com.example.getchatt.data.dto.User
import com.example.getchatt.presentation.screens.Screens
import com.example.getchatt.ui.theme.RoyalBlue
import com.example.getchatt.ui.theme.White
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun GRegistrationScreen(navController: NavController,context:ComponentActivity) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomSheetScaffold(sheetContent = { register(context, navController) }, content = { Images(navController)}, sheetShape = (if (sheetState.isAnimationRunning || sheetState.isVisible){
        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
    }else{
        RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
    }) as Shape,
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = if (sheetState.isAnimationRunning || sheetState.isVisible) 0.dp else 180.dp
    )
}

@Composable
private fun Images(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
        //contentAlignment = Alignment.TopCenter
    ){
        Image(
            painter = painterResource(id = R.drawable.gettochatt),
            contentDescription = null,
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp / 1.8f)
                .fillMaxWidth()
        )  
        Text(text = "Already have an Account SIGN IN", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(21).sp), fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp/45))
        LoginSwipe(navController)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginSwipe(navController: NavController) {
    val composableScope = rememberCoroutineScope()
    val squareSize = LocalConfiguration.current.screenWidthDp.dp - 40.dp
    val swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1, -sizePx to 2)
    val x = remember {
        mutableStateOf(Density(0f))
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .width(LocalConfiguration.current.screenWidthDp.dp - 40.dp)
            .height(LocalConfiguration.current.screenHeightDp.dp / 15)
            .background(White)
            .swipeable(
                state = swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ ->
                    FractionalThreshold(1.0f)
                },
                orientation = Orientation.Horizontal
            )
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SIGN IN",
                color = RoyalBlue,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = LocalConfiguration.current.fontScale.times(25).sp
                ),
                modifier = Modifier.clickable {
                    navController.navigate(Screens.GLoginScreen.route)
                }
            )
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = swipeAbleState.offset.value.roundToInt(), 0
                    )
                }
                .clip(RoundedCornerShape(100.dp))
                .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                .width(LocalConfiguration.current.screenHeightDp.dp / 12)
                .border(1.dp, RoyalBlue, RoundedCornerShape(10.dp))
        ){
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier
                .fillMaxSize()
                .background(
                    RoyalBlue
                )
            )
        }
    }

}

@Composable
private fun register(context: ComponentActivity,navController: NavController) {
    val auth = Firebase.auth
    val emailValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val nameValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val passwordValue = remember {
        mutableStateOf(TextFieldValue())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 2 + 50.dp)
            .background(RoyalBlue),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Let's Get you Registered", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(26).sp), fontWeight = FontWeight.Bold)
        Text(text = "Enter your email and \n set a password", textAlign = TextAlign.Center, style = TextStyle(color = White, fontSize = LocalConfiguration.current.fontScale.times(22).sp))

        OutlinedTextField(
            value = nameValue.value,
            placeholder = { Text(text = "Name", style = TextStyle(fontSize = 16.sp, color = White))},
            onValueChange = { nameValue.value = it },
            label = { Text(text = "NAME", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = LocalConfiguration.current.fontScale.times(18).sp))},
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
        Button(onClick = {
            auth.createUserWithEmailAndPassword(
                emailValue.value.text.trim(), passwordValue.value.text.trim()
            ).addOnCompleteListener(context){task ->
                if (task.isSuccessful){
                    Log.d("AUTH", "Success")
                    addUser(nameValue.value.text.trim(),emailValue.value.text.trim(), userId = auth.currentUser?.uid!!)
                    navController.navigate(Screens.GLoginScreen.route)
                }
                else{
                    Log.w("SignUpWithEmail:Failure", task.exception)
                    Toast.makeText(
                        context, "You Already Have an account Or Wrong credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }, colors = ButtonDefaults.buttonColors(
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

private fun addUser(userName : String,email:String,userId:String){
    val firebaseDatabase = Firebase.database
    val myRef = firebaseDatabase.getReference()

    myRef.child("user").child(userId).setValue(User(userName,email,userId))
}