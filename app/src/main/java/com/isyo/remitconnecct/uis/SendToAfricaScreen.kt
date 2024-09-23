package com.isyo.remitconnecct.uis

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.RemitConnecctTheme
import com.isyo.remitconnecct.uis.recipient.RecipientActivity
import com.isyo.remitconnecct.utils.components.CustomAppBar
import com.isyo.remitconnecct.utils.components.OptionCard

class SendToAfricaScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            RemitConnecctTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = { CustomAppBar(isActionsDisplayed = false, isNavigationIconDisplayed = true) {
                            this@SendToAfricaScreen.finish() }
                    }) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Send to Africa",
                            color = BlueTextColor,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_bold))
                        )
                        Box(Modifier.height(24.dp))
                        Divider(color = GrayBackground)
                        OptionCard(resIcon = R.drawable.icon_arrow_square, title = "Mobile wallets",
                            onClick = {
                                val intent = Intent(context, RecipientActivity::class.java)
                                context.startActivity(intent)
                            })
                        Divider(color = GrayBackground)
                        OptionCard(resIcon = R.drawable.icon_arrow_square, title = "Bank transfer")
                    }

                }
            }
        }
    }
}