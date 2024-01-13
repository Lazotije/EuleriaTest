package com.example.euleriatask.ui.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.theme.buttonBlue
import com.example.euleriatask.ui.theme.white

@Composable
fun DurationButton(duration: Int) {
    Box(
        modifier = Modifier
            .border(width = 8.dp, color = buttonBlue, shape = RoundedCornerShape(size = 15.dp))
            .padding(4.dp)
            .width(150.dp)
            .height(180.dp)
            .background(color = white, shape = RoundedCornerShape(size = 15.dp))
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            .clickable { onClick(duration) },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(150.dp)
                .height(180.dp)
        ) {
            Text(
                modifier = Modifier
                    .width(45.dp)
                    .height(80.dp),
                text = duration.toString(),
                style = TextStyle(
                    fontSize = 72.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontWeight = FontWeight(950),
                    color = buttonBlue,
                    textAlign = TextAlign.Center,
                ),
            )

            Text(
                modifier = Modifier
                    .width(104.dp)
                    .height(36.dp),
                text = stringResource(id = R.string.minutes),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(600),
                    color = black,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

private fun onClick(position: Int) {
    Log.d("LAZA", "KLIK NA POZICIJU " + position)
}

@Preview(showBackground = true)
@Composable
fun DurationButtonPreview() {
    DurationButton(duration = 3)
}
