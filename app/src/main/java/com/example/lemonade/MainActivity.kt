package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        when (currentStep) {
            1 -> {

                LemonTextAndImage(
                    textLabelResourceId = R.string.Lemon_tree,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.Lemonade_one,
                    onImageClick = {

                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.Lemon,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.Lemonade_two,
                    onImageClick = {
                        // Decrease the squeeze count by 1 for each click the user performs
                        squeezeCount--
                        // When we're done squeezing the lemon, move to the next step
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.Glass_of_Lemonade,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.Lemon_tree,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.Empty_Glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.Lemonade_four,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lemonade App")
                        },
                actions = {
                    IconButton(onClick = {
                        Log.d(
                            "ButtonClicked",
                            "Add Button Clicked"
                        )
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            )

        }
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize().background(color = Color.White)
        ) {
            Text(
                text = stringResource(textLabelResourceId),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(
                        onClick = onImageClick
                    )
                    .border(
                        BorderStroke(2.dp, Color(105, 205, 216)),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp)
            )
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun LemonPreview() {
        LemonadeTheme() {

        }
    }
