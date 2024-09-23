package com.isyo.remitconnecct.uis

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.Green2BalanceBg
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.ui.theme.GreenSuccessButtonBg
import com.isyo.remitconnecct.ui.theme.RemitConnecctTheme
import com.isyo.remitconnecct.ui.theme.homeBackground
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