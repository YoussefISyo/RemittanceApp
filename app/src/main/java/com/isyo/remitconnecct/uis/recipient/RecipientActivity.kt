package com.isyo.remitconnecct.uis.recipient

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.app.RemitConnectApp
import com.isyo.remitconnecct.data.RecipientRepository
import com.isyo.remitconnecct.domain.RetrofitService
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.Green2BalanceBg
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.uis.TransferViewModel
import com.isyo.remitconnecct.utils.components.CustomAppBar

class RecipientActivity : ComponentActivity(){

    private lateinit var transferViewModel: TransferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Access the shared ViewModel from the Application class
        transferViewModel = (application as RemitConnectApp).transferViewModel

        setContent {

            val apiService = RetrofitService.getInstance()
            val repository = RecipientRepository(apiService)
            val recipientViewModel: RecipientViewModel = viewModel(factory = RecipientViewModelFactory(repository))
            Scaffold (
                modifier = Modifier
                    .fillMaxSize(),
                topBar = { CustomAppBar(isActionsDisplayed = false, isNavigationIconDisplayed = true) {
                    this@RecipientActivity.finish() } }){paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top
                ){
                    Text(text = "Who are you sending to ?", color = BlueTextColor,
                        fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Box(Modifier.height(24.dp))
                   CustomTabs(recipientViewModel, transferViewModel)
                }

            }
        }
    }

}

@Composable
fun CustomTabs(viewModel: RecipientViewModel, transferViewModel: TransferViewModel) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val list = listOf("Previous Recipients", "New Recipient")

    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = GreenCustom.copy(alpha = 0.2f),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(20))
                .padding(1.dp),
            divider = {},
            indicator = {
                Box {}
            }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = if (selected) Modifier
                        .clip(RoundedCornerShape(20))
                        .background(GreenCustom)
                    else Modifier
                        .clip(RoundedCornerShape(20)),
                    selected = selected,
                    onClick = { selectedIndex = index },
                    text = { Text(text = text, color = if(selected) Color.White else Green2BalanceBg,
                        fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        when (selectedIndex) {
            0 -> { PreviousRecipientScreen(viewModel.recipientsUiState, transferViewModel) }
            1 -> { NewRecipientScreen(transferViewModel) }
        }
    }
}

