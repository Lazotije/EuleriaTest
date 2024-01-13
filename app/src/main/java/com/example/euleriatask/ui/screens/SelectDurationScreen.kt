package com.example.euleriatask.ui.screens


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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.widgets.DurationButton

@Composable
fun SelectDurationScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(113.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(1489.dp)
            .background(color = Color(0xFFEFF0F2))
    ) {
        Text(
            text = stringResource(id = R.string.select_duration),
            modifier = Modifier
                .height(50.dp)
                .width(1490.dp),
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 50.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = black,
                textAlign = TextAlign.Center,
            )
        )
        DurationButtonsRow()
    }
}

@Composable
fun DurationButtonsRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Top,
    ) {
        for (i in 1..5) {
            DurationButton(i, onClick = {

            })
        }
    }
}
