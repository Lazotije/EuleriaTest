package com.example.euleriatask.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.white

@Composable
fun CustomRoundedButton(text: String, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(
                dimensionResource(
                    id = R.dimen.rounded_button_width
                )
            )
            .height(
                dimensionResource(
                    id = R.dimen.rounded_button_height
                )
            )
            .background(
                color = color, shape = RoundedCornerShape(
                    size = dimensionResource(
                        id = R.dimen.rounded_corner_8
                    )
                )
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 32.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
                color = white
            )
        )
    }
}
