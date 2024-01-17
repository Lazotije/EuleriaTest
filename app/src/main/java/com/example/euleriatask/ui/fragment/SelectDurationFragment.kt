package com.example.euleriatask.ui.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.utiils.Screen
import com.example.euleriatask.ui.widgets.DurationButton


@Composable
fun SelectDurationFragment(
    navController: NavController,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(
                id = R.dimen.dp_113
            ), Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(
                dimensionResource(
                    id = R.dimen.select_duration_fragment_width
                )
            )
            .background(color = Color(0xFFEFF0F2))
    ) {
        Text(
            text = stringResource(id = R.string.select_duration),
            modifier = Modifier
                .height(
                    dimensionResource(
                        id = R.dimen.select_duration_text_height
                    )
                )
                .width(
                    dimensionResource(
                        id = R.dimen.select_duration_text_width
                    )
                ),
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 50.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = black,
                textAlign = TextAlign.Center,
            )
        )
        DurationButtonsRow(navController)
    }
}

@Composable
fun DurationButtonsRow(navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(
                dimensionResource(
                    id = R.dimen.duration_button_row_height
                )
            ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(
                id = R.dimen.spacing_50
            ), Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.Top,
    ) {
        for (i in 1..5) {
            DurationButton(i, onClick = { minutes ->
                navController.navigate(Screen.MonitoringScreen.route + "/$minutes")
            })
        }
    }
}
