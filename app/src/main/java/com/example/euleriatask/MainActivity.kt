package com.example.euleriatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.euleriatask.ui.screens.SelectDurationScreen
import com.example.euleriatask.ui.theme.EuleriaTaskTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuleriaTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SelectDurationScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun GreetingPreview() {
    EuleriaTaskTheme {
        SelectDurationScreen()
    }
}