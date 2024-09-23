package com.isyo.remitconnecct

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isyo.remitconnecct.data.storage.SharedPreferences
import com.isyo.remitconnecct.model.User
import com.isyo.remitconnecct.ui.theme.GrayFab
import com.isyo.remitconnecct.ui.theme.GrayIcon
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.ui.theme.YellowCustom
import com.isyo.remitconnecct.uis.BankChoiceActivity
import com.isyo.remitconnecct.uis.mainNavigation.CardsScreen
import com.isyo.remitconnecct.uis.mainNavigation.HomeScreen
import com.isyo.remitconnecct.uis.mainNavigation.Screens
import com.isyo.remitconnecct.uis.mainNavigation.SettingsScreen
import com.isyo.remitconnecct.uis.mainNavigation.TontinesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences = SharedPreferences(context = this.application)
        sharedPreferences.userInfo = User(name = "Youssef Islem", balance = 690.7)

        setContent {
                val context = LocalContext.current
                val navigationController = rememberNavController()
                val selected = remember {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            tonalElevation = 16.dp,
                            containerColor = Color.White,
                            contentColor = Color.White
                        ){
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White,
                                ),
                                label = { Text("Home",
                                    fontWeight = if(selected.intValue == 0) FontWeight.Bold else FontWeight.Normal,
                                    color = if(selected.intValue == 0) GreenCustom else GrayIcon)},
                                modifier = Modifier.weight(1F),
                                selected = selected.intValue == 0,
                                onClick = {
                                    selected.intValue = 0
                                    navigationController.navigate(Screens.Home.screen){
                                        popUpTo(0)
                                    } },
                                icon = {
                                    Icon(painter = painterResource(id = R.drawable.icon_home),
                                        contentDescription = "Home",
                                        tint = if(selected.intValue == 0) GreenCustom else GrayIcon) })
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White,
                                ),
                                label = { Text("Cards",
                                    fontWeight = if(selected.intValue == 1) FontWeight.Bold else FontWeight.Normal,
                                    color = if(selected.intValue == 1) GreenCustom else GrayIcon)},
                                modifier = Modifier.weight(1F),
                                selected = selected.intValue == 1,
                                onClick = {
                                    selected.intValue = 1
                                    navigationController.navigate(Screens.Cards.screen){
                                        popUpTo(0)
                                    } },
                                icon = {
                                    Icon(painter = painterResource(id = R.drawable.icon_credit_card),
                                        contentDescription = "Cards",
                                        tint = if(selected.intValue == 1) GreenCustom else GrayIcon) })

                            Box(
                                modifier = Modifier.background(color = YellowCustom, shape = CircleShape),
                                contentAlignment = Alignment.Center)
                            {
                                FloatingActionButton(
                                    shape = CircleShape,
                                    containerColor = YellowCustom,
                                    onClick = {
                                        // Navigate to another activity
                                        val intent = Intent(context, BankChoiceActivity::class.java)
                                        context.startActivity(intent)
                                    })
                                {
                                    Icon(painter = painterResource(id = R.drawable.icon_send),
                                        contentDescription = null,
                                        tint = GrayFab,
                                        modifier = Modifier.size(24.dp))
                                }
                            }

                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White,
                                ),
                                label = { Text("Tontines",
                                    fontWeight = if(selected.intValue == 2) FontWeight.Bold else FontWeight.Normal,
                                    color = if(selected.intValue == 2) GreenCustom else GrayIcon)},
                                modifier = Modifier.weight(1F),
                                selected = selected.intValue == 2,
                                onClick = {
                                    selected.intValue = 2
                                    navigationController.navigate(Screens.Tontines.screen){
                                        popUpTo(0)
                                    } },
                                icon = {
                                    Icon(painter = painterResource(id = R.drawable.icon_coin),
                                        contentDescription = "Tontines",
                                        tint = if(selected.intValue == 2) GreenCustom else GrayIcon) })

                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White,
                                ),
                                label = { Text("Settings",
                                    fontWeight = if(selected.intValue == 3) FontWeight.Bold else FontWeight.Normal,
                                    color = if(selected.intValue == 3) GreenCustom else GrayIcon) },
                                modifier = Modifier.weight(1F),
                                selected = selected.intValue == 3,
                                onClick = {
                                    selected.intValue = 3
                                    navigationController.navigate(Screens.Settings.screen){
                                        popUpTo(0)
                                    } },
                                icon = {
                                    Icon(painter = painterResource(id = R.drawable.icon_settings),
                                        contentDescription = "Setting",
                                        tint = if(selected.intValue == 3) GreenCustom else GrayIcon)})
                        }
                    }
                ) {paddingValues ->
                    NavHost(navController = navigationController,
                        startDestination = Screens.Home.screen,
                        modifier = Modifier.padding(paddingValues)){
                        composable(Screens.Home.screen){ HomeScreen(sharedPreferences) }
                        composable(Screens.Cards.screen){ CardsScreen() }
                        composable(Screens.Tontines.screen){ TontinesScreen() }
                        composable(Screens.Settings.screen){ SettingsScreen() }
                    }
                }
        }
    }
}