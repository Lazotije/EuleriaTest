package com.example.euleriatask.ui.fragment

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.euleriatask.R
import com.example.euleriatask.ui.fragment.dialog.PauseDialogFragment
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.theme.whiteBg
import com.example.euleriatask.ui.utiils.Utils
import com.example.euleriatask.ui.utiils.extensions.toSeconds
import com.example.euleriatask.ui.viewModel.MonitoringViewModel
import com.example.euleriatask.ui.widgets.EuleriaLineChart
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun MonitoringFragment(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: MonitoringViewModel = koinViewModel()
) {
    val minutes = navBackStackEntry.arguments?.getInt(Utils.MINUTES) ?: 0

    LaunchedEffect(true) {
        viewModel.startSession(minutes.toSeconds())

        viewModel.timerFinished.collect {
            if (it) {
                viewModel.dispose()
                navController.popBackStack()
            }
        }
    }

    var showPauseDialog by remember {
        mutableStateOf(false)
    }

    val heartAndOxy by rememberUpdatedState(newValue = viewModel.heartRateAndOxygen.collectAsState())
    val elapsedTime by viewModel.elapsedTime.collectAsState()
    val heartRateEntryListUpdated by rememberUpdatedState(newValue = viewModel.heartRateEntryListUpdated.collectAsState())
    val saturationEntryListUpdated by rememberUpdatedState(newValue = viewModel.saturationEntryListUpdated.collectAsState())


    if (showPauseDialog) {
        viewModel.pauseSession()

        PauseDialogFragment(
            onDismiss = {

            },

            onNegativeClick = {
                viewModel.dispose()
                showPauseDialog = !showPauseDialog
                navController.popBackStack()
            },

            onPositiveClick = {
                viewModel.resumeSession()
                showPauseDialog = !showPauseDialog
            }
        )
    }

    BackHandler(onBack = {
        viewModel.dispose()
        navController.popBackStack()
    })

    /// UI
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(
                id = R.dimen.spacing_50
            ), Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = whiteBg)
            .clickable {
                showPauseDialog = true
            }
    ) {

        //upper row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    dimensionResource(
                        id = R.dimen.upper_row_height
                    )
                ),
        ) {


            //left side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_rate),
                    contentDescription = "heart rate image",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(
                            dimensionResource(
                                id = R.dimen.padding
                            )
                        )
                        .width(
                            dimensionResource(
                                id = R.dimen.hr_image_width
                            )
                        )
                        .height(
                            dimensionResource(
                                id = R.dimen.hr_image_height
                            )
                        )
                )
                Spacer(
                    modifier = Modifier.width(
                        dimensionResource(
                            id = R.dimen.spacing_16
                        )
                    )
                )
                Image(
                    modifier = Modifier
                        .padding(
                            dimensionResource(
                                id = R.dimen.heart_image_padding
                            )
                        )
                        .width(
                            dimensionResource(
                                id = R.dimen.heart_image_width
                            )
                        )
                        .height(
                            dimensionResource(
                                id = R.dimen.heart_image_height
                            )
                        ),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "heart image",
                    contentScale = ContentScale.None,

                    )
                Spacer(
                    modifier = Modifier.width(
                        dimensionResource(
                            id = R.dimen.spacing_16
                        )
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            dimensionResource(
                                id = R.dimen.padding_18
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.heart_rate),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppins_medium
                                )
                            ),
                            fontWeight = FontWeight(500),
                            color = black
                        ),
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.spacing_16))
                    )
                    Text(
                        text = "${heartAndOxy.value?.rate?.bpm ?: "N/A"} " + stringResource(id = R.string.bpm),
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(700),
                            color = black
                        ),
                        modifier = Modifier
                            .width(
                                dimensionResource(
                                    id = R.dimen.hr_oxy_text_width
                                )
                            )
                            .height(
                                dimensionResource(
                                    id = R.dimen.hr_oxy_text_height
                                )
                            )
                    )
                }
            }


            // right side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            dimensionResource(
                                id = R.dimen.padding_18
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringResource(id = R.string.oxygen_saturation),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(500),
                            color = black,
                        )
                    )
                    Text(
                        text = "${heartAndOxy.value?.saturation?.percentage ?: "N/A"}" + "%",
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.End,
                        )
                    )
                }
                Spacer(
                    modifier = Modifier.width(
                        dimensionResource(
                            id = R.dimen.spacing_16
                        )
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_oxy),
                    contentDescription = "image oxy",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(
                            dimensionResource(
                                id = R.dimen.oxy_image_padding
                            )
                        )
                        .width(
                            dimensionResource(
                                id = R.dimen.oxy_image_width
                            )
                        )
                        .height(
                            dimensionResource(
                                id = R.dimen.oxy_image_height
                            )
                        )

                )
                Spacer(
                    modifier = Modifier.width(
                        dimensionResource(
                            id = R.dimen.spacing_16
                        )
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_oxy_line),
                    contentDescription = "oxygen line image",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(
                            dimensionResource(
                                id = R.dimen.padding
                            )
                        )
                        .width(
                            dimensionResource(
                                id = R.dimen.oxy_line_image_width
                            )
                        )
                        .height(
                            dimensionResource(
                                id = R.dimen.oxy_line_image_height
                            )
                        )
                )
            }
        }

        //graph
        EuleriaLineChart(
            heartRateEntryListUpdated,
            saturationEntryListUpdated,
            minutes.toSeconds().toFloat()
        ) {
            showPauseDialog = true
        }

        //time row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(
                        id = R.dimen.horizontal_padding
                    ), end = dimensionResource(
                        id = R.dimen.horizontal_padding
                    )
                )
        ) {

            //elapsed time
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = elapsedTime,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = black
                    )
                )
            }

            //total time
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$minutes " + stringResource(id = R.string.minute),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = black,
                        textAlign = TextAlign.Right,
                    )
                )
            }
        }
    }
}