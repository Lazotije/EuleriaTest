package com.example.euleriatask

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.euleriatask.ui.fragment.MonitoringFragment
import com.example.euleriatask.ui.fragment.SelectDurationFragment
import com.example.euleriatask.ui.theme.EuleriaTaskTheme
import com.example.euleriatask.ui.utiils.Screen
import com.example.euleriatask.ui.utiils.Utils
import com.example.euleriatask.ui.widgets.EuleriaLineChart
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.delay


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
            composable(Screen.SelectDurationScreen.route) {
                SelectDurationFragment(navController)
//                EuleriaLineChart(Entry(1f, 20f))
            }

            //Monitoring fragment
            composable(Screen.MonitoringScreen.route + "/{minutes}",
                arguments = listOf(navArgument(Utils.MINUTES) { type = NavType.IntType })
            ) { backStackEntry ->
                MonitoringFragment(navController, backStackEntry)
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