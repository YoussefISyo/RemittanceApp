package com.isyo.remitconnecct.uis

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.RemitConnecctTheme
import com.isyo.remitconnecct.utils.components.CustomAppBar
import com.isyo.remitconnecct.utils.components.OptionCard

class BankChoiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val context = LocalContext.current
            RemitConnecctTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = { CustomAppBar(
                        isActionsDisplayed = true,
                        isNavigationIconDisplayed = false
                    ) {
                        this@BankChoiceActivity.finish()
                    } }) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top
                    ){
                        Spacer(Modifier.height(8.dp))
                        Text(text = "Send money", color = BlueTextColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.outfit_bold)),)
                        Spacer(Modifier.height(24.dp))
                        Divider(color = GrayBackground)
                        OptionCard(resIcon = R.drawable.icon_user, title = "To Moneco balance")
                        Divider(color = GrayBackground)
                        OptionCard(resIcon = R.drawable.icon_store, title = "Bank transfer")
                        Divider(color = GrayBackground)
                        OptionCard(resIcon = R.drawable.icon_world, title = "Send to Africa",
                            onClick = {
                                val intent = Intent(context, SendToAfricaScreen::class.java)
                                context.startActivity(intent)
                            })
                    }
                }
            }
        }
    }
}