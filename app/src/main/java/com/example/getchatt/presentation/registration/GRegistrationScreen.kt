package com.example.getchatt.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.example.getchatt.R
import com.example.getchatt.ui.theme.RoyalBlue
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun GRegistrationScreen() {
    val swipeableState = rememberSwipeableState(initialValue = States.COLLAPSED)
    val scrollState = rememberScrollState()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Images()
        }
//        FullHeightBottomSheet(header = { Images() }){
//            Text(text = "Hi how are you")
//        }
        val constraintsScope = this
        val maxHeight = with(LocalDensity.current) {
            constraintsScope.maxHeight.toPx()
        }
        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipeableState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipeableState.performDrag(delta).toOffset()
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    return if (available.y < 0 && scrollState.value == 0) {
                        swipeableState.performFling(available.y)
                        available
                    } else {
                        Velocity.Zero
                    }
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipeableState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .swipeable(
                    state = swipeableState,
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to States.EXPANDED,
                        maxHeight to States.COLLAPSED,
                    )
                )
                .nestedScroll(connection)
                .offset {
                    IntOffset(
                        0,
                        swipeableState.offset.value.roundToInt()
                    )
                }
                .height(LocalConfiguration.current.screenHeightDp.dp / 2)
                .clip(RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp))
                .background(RoyalBlue),
            contentAlignment = Alignment.Center
        ) {
            val emailValue = remember {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = emailValue.value, onValueChange = {emailValue.value})
            }
        }
    }
}

@Composable
fun Images() {
    Image(
        painter = painterResource(id = R.drawable.gettochatt),
        contentDescription = null,
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp / 1.8f)
            .fillMaxWidth()
    )
}

enum class BoxStates{
    EXPANDED,
    STILL
}

enum class States {
    EXPANDED,
    COLLAPSED
}

@ExperimentalMaterialApi
@Composable
fun FullHeightBottomSheet(
    header: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    val swipeableState = rememberSwipeableState(initialValue = States.COLLAPSED)
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        val constraintsScope = this
        val maxHeight = with(LocalDensity.current) {
            constraintsScope.maxHeight.toPx()
        }

        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipeableState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipeableState.performDrag(delta).toOffset()
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    return if (available.y < 0 && scrollState.value == 0) {
                        swipeableState.performFling(available.y)
                        available
                    } else {
                        Velocity.Zero
                    }
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipeableState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        Box(
            Modifier
                .swipeable(
                    state = swipeableState,
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to States.EXPANDED,
                        maxHeight to States.COLLAPSED,
                    )
                )
                .nestedScroll(connection)
                .offset {
                    IntOffset(
                        3,
                        swipeableState.offset.value.roundToInt()
                    )
                }
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                header()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {
                    body()
                }
            }
        }
    }
}