package com.isyo.remitconnecct.uis.mainNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.data.storage.SharedPreferences
import com.isyo.remitconnecct.ui.theme.Blue2TextColor
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.GrayIcon
import com.isyo.remitconnecct.ui.theme.Green2BalanceBg
import com.isyo.remitconnecct.ui.theme.GreenBalanceBg
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.ui.theme.RemitConnecctTheme
import com.isyo.remitconnecct.ui.theme.homeBackground
import com.isyo.remitconnecct.utils.components.DashboardCard
import com.isyo.remitconnecct.utils.components.TransactionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    sharedPreferences: SharedPreferences
) {

    val gradient = Brush.linearGradient(
        0.0f to GreenBalanceBg,
        500.0f to Green2BalanceBg,
        start = Offset.Zero,
        end = Offset.Infinite
    )

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            homeBackground
        )
    )

    RemitConnecctTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = { TopAppBar(title = { Box(modifier = Modifier) }) }) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGradient)
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hey, ${sharedPreferences.userInfo?.name}",
                            color = BlueTextColor,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_bold))
                        )
                        Box(
                            modifier = Modifier
                                .background(color = GrayBackground, shape = RoundedCornerShape(20))
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(6.dp),
                                painter = painterResource(id = R.drawable.icon_bell),
                                tint = BlueTextColor,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .background(gradient, shape = RoundedCornerShape(10))
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Your balance",
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.outfit_regular)),
                                    fontSize = 16.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = GrayBackground.copy(alpha = 0.25f),
                                            shape = RoundedCornerShape(20)
                                        )
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(8.dp),
                                        painter = painterResource(
                                            id = R.drawable.icon_moneys
                                        ),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                            Text(
                                text = sharedPreferences.userInfo?.balance.toString(),
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.outfit_bold)),
                                fontSize = 24.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Euro", color = Color.White,
                                fontFamily = FontFamily(Font(R.font.outfit_regular)),
                                fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(Modifier.height(32.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(8.dp)
                            .height(250.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            DashboardCard(
                                iconRes = R.drawable.icon_empty_wallet_add,
                                title = "Top up balance"
                            )
                        }
                        item {
                            DashboardCard(
                                iconRes = R.drawable.icon_wallet_minus,
                                title = "Withdraw money"
                            )
                        }
                        item {
                            DashboardCard(iconRes = R.drawable.icon_card, title = "Get IBAN")
                        }
                        item {
                            DashboardCard(
                                iconRes = R.drawable.icon_percentage_square,
                                title = "View analytics"
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Transactions", fontWeight = FontWeight.Bold, color = BlueTextColor,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TransactionCard("Ralph Edwards", 150)
                    TransactionCard("Kwame Kounou", 360)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

