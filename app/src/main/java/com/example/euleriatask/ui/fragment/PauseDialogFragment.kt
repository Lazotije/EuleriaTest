package com.example.euleriatask.ui.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
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
import com.example.euleriatask.ui.theme.darkBlue
import com.example.euleriatask.ui.theme.red
import com.example.euleriatask.ui.theme.white
import com.example.euleriatask.ui.viewModel.SelectDurationViewModel
import com.example.euleriatask.ui.widgets.CustomRoundedButton
import org.koin.androidx.compose.getViewModel

@Composable
fun PauseDialogFragment(viewModel: SelectDurationViewModel = getViewModel()) {
    Column(
        verticalArrangement = Arrangement.spacedBy(55.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(460.dp)
            .height(355.dp)
            .background(color = white, shape = RoundedCornerShape(size = 15.dp))
    ) {
        Text(
            modifier = Modifier
                .width(344.dp)
                .height(IntrinsicSize.Min),
            text = stringResource(id = R.string.pause),
            style = TextStyle(
                fontSize = 45.sp,
                lineHeight = 40.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(700),
                color = black,
                textAlign = TextAlign.Center,
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .width(350.dp)
                .height(180.dp)
        ) {
            CustomRoundedButton(text = stringResource(id = R.string.continue_button_txt),
                color = darkBlue,
                onClick = {
                    viewModel.resumeSession()
                })
            CustomRoundedButton(
                text = stringResource(id = R.string.quit_button_txt),
                color = red,
                onClick = {
                    viewModel.cancelSession()
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PauseDialogPreview() {
    PauseDialogFragment()
}