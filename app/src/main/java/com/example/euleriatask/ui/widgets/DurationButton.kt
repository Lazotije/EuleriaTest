package com.example.euleriatask.ui.widgets

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.theme.buttonBlue
import com.example.euleriatask.ui.theme.white

@Composable
fun DurationButton(duration: Int, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .border(
                width = dimensionResource(
                    id = R.dimen.border_width
                ), color = buttonBlue, shape = RoundedCornerShape(
                    size = dimensionResource(
                        id = R.dimen.dp_15
                    )
                )
            )
            .padding(
                dimensionResource(
                    id = R.dimen.padding
                )
            )
            .width(
                dimensionResource(
                    id = R.dimen.dp_150
                )
            )
            .height(
                dimensionResource(
                    id = R.dimen.dp_180
                )
            )
            .background(
                color = white, shape = RoundedCornerShape(
                    size = dimensionResource(
                        id = R.dimen.dp_15
                    )
                )
            )
            .padding(
                dimensionResource(
                    id = R.dimen.padding_10
                )
            )
            .clickable { onClick(duration) },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(
                    id = R.dimen.dp_20
                ), Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(
                    dimensionResource(
                        id = R.dimen.dp_150
                    )
                )
                .height(
                    dimensionResource(
                        id = R.dimen.dp_180
                    )
                )
        ) {
            Text(
                modifier = Modifier
                    .width(
                        dimensionResource(
                            id = R.dimen.duration_text_width
                        )
                    )
                    .height(
                        dimensionResource(
                            id = R.dimen.duration_text_height
                        )
                    ),
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
                    .width(
                        dimensionResource(
                            id = R.dimen.minutes_text_width
                        )
                    )
                    .height(
                        dimensionResource(
                            id = R.dimen.minutes_text_height
                        )
                    ),
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

@Preview(showBackground = true)
@Composable
fun DurationButtonPreview() {
    DurationButton(duration = 3, onClick = {
    })
}
