package com.example.euleriatask.ui.fragment.dialog

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.theme.darkBlue
import com.example.euleriatask.ui.theme.red
import com.example.euleriatask.ui.theme.white
import com.example.euleriatask.ui.widgets.CustomRoundedButton

@Composable
fun PauseDialogFragment(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        PauseDialogFragmentUi(
            onNegativeClick = onNegativeClick,
            onPositiveClick = onPositiveClick
        )
    }
}

@Composable
fun PauseDialogFragmentUi(
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_55), Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dialog_width))
            .height(dimensionResource(id = R.dimen.dialog_height))
            .background(color = white, shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.dp_15)))
    ) {
        Text(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.pause_text_view_width))
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
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_40), Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.dp_350))
                .height(dimensionResource(id = R.dimen.dp_180))
        ) {
            CustomRoundedButton(text = stringResource(id = R.string.continue_button_txt),
                color = darkBlue,
                onClick = {
                    onPositiveClick()
                })
            CustomRoundedButton(
                text = stringResource(id = R.string.quit_button_txt),
                color = red,
                onClick = {
                    onNegativeClick()
                })
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PauseDialogPreview() {
    PauseDialogFragmentUi({}, {}
    )
}