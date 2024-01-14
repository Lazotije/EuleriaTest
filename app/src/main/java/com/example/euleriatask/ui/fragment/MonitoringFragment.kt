package com.example.euleriatask.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.euleriatask.R
import com.example.euleriatask.ui.theme.black
import com.example.euleriatask.ui.theme.whiteBg
import com.example.euleriatask.ui.viewModel.SelectDurationViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun MonitoringFragment(
    navController: NavController,
    viewModel: SelectDurationViewModel = koinViewModel()
) {

    val heartAndOxy by viewModel.heartRateAndOxygen.collectAsState()
    var showPauseDialog by remember {
        mutableStateOf(false)
    }

    if (showPauseDialog) {
        PauseDialogFragment(navController = navController, openDialogCustom = mutableStateOf(true))
    }


    Column(
        verticalArrangement = Arrangement.spacedBy(113.dp, Alignment.CenterVertically),
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
                .height(200.dp),
        ) {


            //left side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_rate),
                    contentDescription = "heart rate image",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(112.5.dp)
                        .height(110.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    modifier = Modifier
                        .padding(1.71552.dp)
                        .width(81.dp)
                        .height(71.dp),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "heart image",
                    contentScale = ContentScale.None,

                    )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(18.dp)
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
                            .width(204.dp)
                            .height(53.dp)
                    )
                    Text(
                        text = "${heartAndOxy?.rate?.bpm} " + stringResource(id = R.string.bpm),
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(700),
                            color = black
                        ),
                        modifier = Modifier
                            .width(268.dp)
                            .height(108.dp)
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
                        .padding(18.dp),
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
                        text = "${heartAndOxy?.saturation?.percentage}" + "%",
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.End,
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_oxy),
                    contentDescription = "image oxy",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(1.71552.dp)
                        .width(69.dp)
                        .height(69.dp)

                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_oxy_line),
                    contentDescription = "oxygen line image",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(115.dp)
                        .height(79.5.dp)
                )
            }
        }

        //todo here graph goes
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(643.dp)
                .padding(bottom = 88.dp, start = 45.dp, end = 45.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun MonitoringFragmentPreview() {
    val navController = rememberNavController()
    MonitoringFragment(navController = navController)
}