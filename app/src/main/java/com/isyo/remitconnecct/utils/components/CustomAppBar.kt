package com.isyo.remitconnecct.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(isActionsDisplayed: Boolean, isNavigationIconDisplayed: Boolean, onButtonClicked: () -> Unit){
    TopAppBar(title = { Box(modifier = Modifier) },
        actions = {
            if (isActionsDisplayed) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Box(modifier = Modifier
                        .background(
                            color = GrayBackground,
                            shape = RoundedCornerShape(20)
                        )
                        .padding(8.dp)
                        .clickable {
                            onButtonClicked()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_close),
                            contentDescription = null,
                            tint = BlueTextColor
                        )

                    }
                }
            }
        },
        navigationIcon = {
            if(isNavigationIconDisplayed){
                Row(
                    Modifier.padding(start = 16.dp)
                ) {
                    Box(modifier = Modifier
                        .background(
                            color = GrayBackground,
                            shape = RoundedCornerShape(20)
                        )
                        .padding(8.dp)
                        .clickable {
                           onButtonClicked()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_left),
                            contentDescription = null,
                            tint = BlueTextColor
                        )

                    }
                }
            }
        })
}