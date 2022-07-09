package com.example.getchatt.presentation.chatt

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
import androidx.navigation.NavController
import com.example.getchatt.data.dto.Message
import com.example.getchatt.ui.theme.RoyalBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun GChatScreen(navController:NavController,name:String, receiverUid:String) {
    val messageValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val messageList : MutableList<Message> = remember {
        mutableStateListOf()
    }

    val senderUid = FirebaseAuth.getInstance().currentUser!!.uid
    val mDbRef = FirebaseDatabase.getInstance().getReference()

    val senderRoom  = remember {
        mutableStateOf(receiverUid + senderUid)
    }
    val receiverRoom  = remember {
        mutableStateOf(senderUid + receiverUid)
    }

    mDbRef.child("chats").child(senderRoom.toString()).child("messages").addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            
            messageList.clear()
            GlobalScope.launch(Dispatchers.IO) {
                for (postSnapshot in snapshot.children){
                    val message = postSnapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                }
            }


        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })


    Scaffold(
        topBar = {
                 TopAppBar() {
                     Row(
                         modifier = Modifier
                             .width(LocalConfiguration.current.screenWidthDp.dp)
                             .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                             .background(Color.Black),
                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
                     ) {
                         Icon(imageVector = Icons.Default.ArrowBack,
                             contentDescription = null,
                             modifier = Modifier
                                 .clickable {
                                     navController.popBackStack()
                                 }
                                 .height(30.dp)
                                 .width(30.dp),
                             tint = Color.White
                         )
                         Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                             Text(text = name, style = TextStyle(color = Color.White, fontSize = 30.sp))
                         }
                     }
                 }
        },
        bottomBar = { BottomAppBar(modifier = Modifier.background(Color.Black)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
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
                    .width(LocalConfiguration.current.screenWidthDp.dp - 90.dp)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                    .focusRequester(
                        FocusRequester()
                    ),
                shape = RoundedCornerShape(30.dp)
            )
            Box(
                modifier = Modifier
                    .clickable {
                        val message = messageValue.value.text.toString()
                        val messageObject = Message(message, senderUid)

                        mDbRef
                            .child("chats")
                            .child(senderRoom.toString())
                            .child("messages")
                            .push()
                            .setValue(messageObject)
                            .addOnSuccessListener {
                                mDbRef
                                    .child("chats")
                                    .child(receiverRoom.toString())
                                    .child("messages")
                                    .push()
                                    .setValue(messageObject)
                            }
                        messageValue.value = TextFieldValue("")

                    }
                    .width(LocalConfiguration.current.screenWidthDp.dp / 5)
                    .height(LocalConfiguration.current.screenHeightDp.dp / 12)
                    .clip(RoundedCornerShape(30.dp))
                    .background(RoyalBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "send", tint = Color.White, modifier = Modifier.size(30.dp))
            }
        }
    }}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier =Modifier.padding(bottom = 10.dp))
            Column(modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp - 70.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()), 
                verticalArrangement = Arrangement.Bottom) {
            
                messageList.forEachIndexed { index, message ->
                    if (FirebaseAuth.getInstance().currentUser?.uid.equals(message.senderId) ){
                            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                                send(message.message!!)
                            }

                    }
                    else {
                         recieve(message.message!!)
                    }
                    Spacer(modifier = Modifier.padding(bottom = 25.dp))
                }
                
            }
            Spacer(modifier = Modifier.padding(35.dp))
        }

    }

}



@Composable
private fun send(value : String) {
    Row(modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(Color.White), verticalAlignment = Alignment.CenterVertically) {
        Text(text = value, color = RoyalBlue, fontSize = 15.sp, modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp), fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun recieve(value : String) {
    Row(modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(RoyalBlue), verticalAlignment = Alignment.CenterVertically) {
        Text(text = value, color = Color.White, fontSize = 15.sp, modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp), fontWeight = FontWeight.Bold)
    }
}