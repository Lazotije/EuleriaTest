package com.example.euleriatask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.euleriatask.ui.fragment.MonitoringFragment
import com.example.euleriatask.ui.fragment.PauseDialogFragment
import com.example.euleriatask.ui.fragment.PauseDialogFragmentUi
import com.example.euleriatask.ui.fragment.SelectDurationFragment
import com.example.euleriatask.ui.theme.EuleriaTaskTheme
import com.example.euleriatask.ui.utiils.Utils


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

                    val navController = rememberNavController()
                    MyAppNavHost(navController = navController)
                }
            }
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun MyAppNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Utils.FIRST_SCREEN_ROUTE) {

            //Select duration fragment
            composable(Utils.FIRST_SCREEN_ROUTE) {
                SelectDurationFragment(navController)
            }

            //Monitoring fragment
            composable(Utils.MONITORING_SCREEN_ROUTE) {
                MonitoringFragment(navController)
            }

            //Pause dialog fragment
            composable(Utils.DIALOG_SCREEN_ROUTE) {
                PauseDialogFragment(navController, mutableStateOf(false))
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun GreetingPreview() {
    EuleriaTaskTheme {
        val navController = rememberNavController()
        SelectDurationFragment(navController)
    }
}