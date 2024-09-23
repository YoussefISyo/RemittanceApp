package com.isyo.remitconnecct.uis.wallet

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.app.RemitConnectApp
import com.isyo.remitconnecct.data.WalletRepository
import com.isyo.remitconnecct.domain.RetrofitService
import com.isyo.remitconnecct.model.Wallet
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.uis.CheckoutActivity
import com.isyo.remitconnecct.uis.TransferViewModel
import com.isyo.remitconnecct.uis.recipient.LoadingScreen
import com.isyo.remitconnecct.utils.components.CustomAppBar
import retrofit2.Response

class MobileWalletsActivity : ComponentActivity(){

    private lateinit var transferViewModel: TransferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val apiService = RetrofitService.getInstance()
            val repository = WalletRepository(apiService)
            val walletViewModel: WalletViewModel = viewModel(factory = WalletViewModelFactory(repository))
            val context = LocalContext.current

            transferViewModel = (application as RemitConnectApp).transferViewModel

            var selectedItem by remember { mutableStateOf<String?>(null) }

            Scaffold (
                bottomBar =
                {
                    Button(
                        enabled = selectedItem.isNullOrEmpty().not(),
                        onClick = {
                            val intent = Intent(context, CheckoutActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00754E))
                    ) {
                        Text(text = "Continue", color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp),)
                    }
                },
                modifier = Modifier
                    .fillMaxSize(),
                topBar = { CustomAppBar(isActionsDisplayed = false, isNavigationIconDisplayed = true) {
                    this@MobileWalletsActivity.finish() } }){paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top
                ){
                    when (walletViewModel.walletsUiState) {
                        is WalletsUiState.Loading -> LoadingScreen()
                        is WalletsUiState.Success -> WalletsList((walletViewModel.walletsUiState as WalletsUiState.Success).wallets,
                            selectedItem,
                            onClick = { walletName ->
                                transferViewModel.updateWallet(walletName)
                                selectedItem = walletName
                            })
                        is WalletsUiState.Error -> Box{}
                    }
                }

            }
        }
    }
}

@Composable
private fun WalletsList(
    wallets: Response<List<Wallet>>,
    selectedItem: String?,
    onClick: (String) -> Unit
) {

    Text(text = "Choose a mobile Wallet", color = BlueTextColor,
        fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.outfit_bold)),)
    Spacer(Modifier.height(24.dp))
    wallets.body()?.forEachIndexed{ index, wallet ->
        WalletCard(resIcon = R.drawable.wallet_placeholder, title = wallet.name,
            onClick = {
                onClick(wallet.name)
            },
            isSelected = selectedItem == wallet.name)
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
fun WalletCard(resIcon: Int, title: String, onClick: () -> Unit = {}, isSelected : Boolean){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if(isSelected) GreenCustom.copy(alpha = 0.1f) else Color.Transparent
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if(isSelected) GreenCustom else Color.Gray
        ),
        modifier = Modifier.clickable {
            onClick()
        }
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = resIcon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20))
                    .size(40.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(text = title,
                color = BlueTextColor,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                fontSize = 16.sp)
        }
    }
}