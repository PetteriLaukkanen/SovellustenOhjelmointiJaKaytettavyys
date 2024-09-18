package com.example.ssovellus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ssovellus.ui.theme.SääsovellusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Menu() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color.LightGray)
                .padding(5.dp)

        ){
        Text(
            text = "Sääsovellus",
            modifier = Modifier.padding(16.dp)
        )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0x88FFEB3B))
                .padding(5.dp)

        ) {
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.sunny),
                    contentDescription = "Sunny",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Aurinkoista",
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "Tuuli: 8 m/s",
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 5.dp)
                )
            }

        }
                    Button(onClick = {}) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                    }

    }
}