package com.example.getchatt.presentation.chatt

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.getchatt.MainActivity
import com.example.getchatt.presentation.GViewModel
import com.example.getchatt.presentation.screens.Screens
import com.example.getchatt.ui.theme.RoyalBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GChattListScreen(navController: NavController) {

    var li = listOf("Gautam","Pritam","Samuel","Niranjan","Karthik","Ajay","Sonal","Srinidhi","KD","Thatha")

    val mAuth = FirebaseAuth.getInstance()
    val mDbRef = FirebaseDatabase.getInstance().getReference()
    mDbRef.child("user").addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {



        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
    val verticalScroll = rememberScrollState()
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier
                .background(Color.Black)
                .width(LocalConfiguration.current.screenWidthDp.dp)
                .height(50.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Messages", style = TextStyle(color = Color.White, fontSize = 25.sp), modifier = Modifier.padding(start = 20.dp))
            }
        }
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                },
                shape = RoundedCornerShape(50),
                backgroundColor = Color.White
            ) {
                Icon(Icons.Filled.Home, tint = RoyalBlue, contentDescription = "Home")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                cutoutShape = RoundedCornerShape(50),
                content = {
                    BottomNavigation {
                        BottomNavigationItem(
                            selected = true,
                            onClick = {},
                            icon = {
                                Icon(Icons.Filled.Person, contentDescription = "Profile")
                            },
                            label = { Text(text = "Profile") },
                            alwaysShowLabel = false,
                            modifier = Modifier.background(Color.Black)
                        )

                        BottomNavigationItem(
                            selected = true,
                            onClick = {
                                    navController.navigate(Screens.Settings.route)
                            },
                            icon = {
                                Icon(Icons.Filled.Settings, contentDescription = "setting")
                            },
                            label = { Text(text = "Setting") },
                            alwaysShowLabel = false,
                            modifier = Modifier.background(Color.Black)
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(verticalScroll),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            Spacer(modifier = Modifier.padding(30.dp))
        }
    }
}

@Composable
fun Settings(navController: NavController,viewModel: GViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 40.dp)
                .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .clickable {

                    }
                    .height(30.dp)
                    .width(30.dp),
                tint = Color.White
            )
            Text(text = "Settings", style = TextStyle(color = Color.White, fontSize = 30.sp), modifier = Modifier.padding(start = 80.dp))
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Divider(modifier = Modifier
            .width(220.dp)
            .height(3.dp)
            .background(RoyalBlue))
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            modifier = Modifier
                .clickable {
                    FirebaseAuth.getInstance().signOut()
                    viewModel.deleteUid()
                    navController.navigate(Screens.GRegistrationScreen.route)
//                    mAuth.currentUser?.apply {
//                        delete().addOnCompleteListener(){task ->
//                            if (task.isSuccessful){
//                                Log.d("SignOut", "Success")
//                                Toast.makeText(
//                                    context, "Successfully Signed Out",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                navController.navigate(Screens.GRegistrationScreen.route)
//                            }
//                            else{
//                                Log.w("SignOUT:Failure", task.exception)
//                                Toast.makeText(
//                                    context, "Sorry SignOut not possible",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    }
                }
                .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                .width(
                    LocalConfiguration.current.screenWidthDp.dp - 40.dp
                )
                .border(width = 2.dp, color = RoyalBlue, shape = RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LOGOUT",
                modifier = Modifier.padding(start = 15.dp),
                style = TextStyle(color = Color.White, fontSize = 25.sp)
            )
        }
    }
}
