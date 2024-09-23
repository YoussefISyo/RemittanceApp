package com.isyo.remitconnecct.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.ui.theme.GreenSuccessButtonBg

@Composable
fun OptionCard(resIcon: Int, title: String, onClick: () -> Unit = {}){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.clickable {
            onClick()
        }
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier.background(color = GreenCustom.copy(0.1f), shape = RoundedCornerShape(20)),
                ){
                    Icon(painter = painterResource(id = resIcon),
                        contentDescription = null,
                        tint = GreenSuccessButtonBg,
                        modifier = Modifier.padding(8.dp))
                }
                Spacer(Modifier.width(16.dp))
                Text(text = title,
                    color = BlueTextColor,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),)
            }
            Icon(painter = painterResource(id = R.drawable.icon_arrow),
                contentDescription = null,
                tint = BlueTextColor
            )
        }
    }
}