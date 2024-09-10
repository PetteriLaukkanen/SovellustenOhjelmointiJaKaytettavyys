package com.example.viikkoharjoitukset1_t2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.viikkoharjoitukset1_t2.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Menu()
        }
    }
}

@Composable
@Preview
fun Menu() {
    var text by remember { mutableStateOf("") }
    var showHello by remember { mutableStateOf(false) }

    LaunchedEffect(showHello) {
        if (showHello) {
            delay(2000)
            text = ""
            showHello = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color.LightGray)
                .padding(30.dp)
        ) {
            Text(
                text = "T2-sovellus",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                color = Purple40
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = if (showHello) "Hello!" else text,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                text = ""
                showHello = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Press to say Hello!", fontSize = 20.sp)
        }
    }
}
