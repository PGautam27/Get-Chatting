package com.example.getchatt.presentation.chatt

import android.provider.Settings
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.getchatt.presentation.screens.Screens
import com.example.getchatt.ui.theme.RoyalBlue


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GChattListScreen(navController: NavController) {

    var li = listOf("Gautam","Pritam","Samuel","Niranjan","Karthik","Ajay","Sonal","Srinidhi","KD","Thatha")
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
fun Settings() {
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
                    .clickable { }
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
                .clickable { }
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
