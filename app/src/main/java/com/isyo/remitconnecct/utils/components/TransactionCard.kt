package com.isyo.remitconnecct.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.Blue2TextColor
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayIcon

@Composable
fun TransactionCard(recipientName: String, amount : Int){
    Card(
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier.background(color = Blue2TextColor.copy(0.1f),
                        shape = RoundedCornerShape(20)
                    )
                ){
                    Icon(painter = painterResource(id = R.drawable.icon_arrow_up_right),
                        contentDescription = null,
                        tint = Blue2TextColor,
                        modifier = Modifier.padding(8.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sent to", color = GrayIcon,
                        fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.outfit_regular))
                    )
                    Text(recipientName, color = BlueTextColor,
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                    )
                }
            }
            Text("€ $amount", color = BlueTextColor,
                fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.outfit_medium))
            )
        }
    }
}