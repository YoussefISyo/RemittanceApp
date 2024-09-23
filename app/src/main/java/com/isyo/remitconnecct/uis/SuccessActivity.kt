package com.isyo.remitconnecct.uis

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.MainActivity
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.Green2BalanceBg
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.ui.theme.GreenSuccessButtonBg

class SuccessActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            Scaffold (
                containerColor = GreenCustom
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Icon(painter = painterResource(id = R.drawable.icon_success), contentDescription = null)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Your money is on the way. Get excited!",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.outfit_bold)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .background(shape = RoundedCornerShape(8), color = GreenSuccessButtonBg),
                        colors = ButtonDefaults.buttonColors(containerColor = GreenSuccessButtonBg)
                    ) {
                        Text(text = "Got it !", color = Color.White)
                    }
                }
            }
        }
    }
}