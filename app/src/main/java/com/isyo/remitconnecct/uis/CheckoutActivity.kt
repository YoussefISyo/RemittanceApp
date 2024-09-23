package com.isyo.remitconnecct.uis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.app.RemitConnectApp
import com.isyo.remitconnecct.data.storage.SharedPreferences
import com.isyo.remitconnecct.ui.theme.Blue2TextColor
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.GrayIcon
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.utils.components.CustomAppBar
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class CheckoutActivity : ComponentActivity(){

    private lateinit var transferViewModel: TransferViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var amount by remember { mutableIntStateOf(0) }
            var total by remember { mutableDoubleStateOf(0.0) }

            val conversionRate by remember { mutableDoubleStateOf(655.94) }

            val context = LocalContext.current
            val sharedPreferences = SharedPreferences(context = context)

            transferViewModel = (application as RemitConnectApp).transferViewModel

            val nameCollectState by transferViewModel.nameState.collectAsState()
            val walletCollectState by transferViewModel.walletState.collectAsState()
            val amountCollectState by transferViewModel.amountState.collectAsState()
            val phoneCollectState by transferViewModel.phoneState.collectAsState()

            val scaffoldState = rememberBottomSheetScaffoldState(
                SheetState(
                    skipHiddenState = false,
                    skipPartiallyExpanded = true,
                    initialValue = SheetValue.Hidden
                )
            )
            val scope = rememberCoroutineScope()

            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {
                    BottomSheet(context, nameCollectState, amountCollectState,
                        walletCollectState, phoneCollectState)
                },
                sheetPeekHeight = 0.dp, // Make the bottom sheet hidden by default,
            ){
                Scaffold (
                    bottomBar =
                    {
                        Button(
                            enabled = checkAmount(amount, sharedPreferences.userInfo?.balance),
                            onClick = {
                                transferViewModel.updateAmount(total)
                                      scope.launch {
                                          scaffoldState.bottomSheetState.expand()
                                      }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00754E))
                        ) {
                            Text(text = "Send", color = Color.White,
                                modifier = Modifier.padding(vertical = 8.dp),)
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = { CustomAppBar(
                        isActionsDisplayed = false,
                        isNavigationIconDisplayed = true
                    ) {
                        this@CheckoutActivity.finish()
                    } }
                ){paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top
                    ){
                        Text(text = "Send money", color = BlueTextColor,
                            fontSize = 24.sp, fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                        )
                        Spacer(Modifier.height(24.dp))
                        Text(text = "How much are you sending ?", color = BlueTextColor,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                        )
                        Spacer(Modifier.height(8.dp))
                        AmountCard(amount, sharedPreferences,
                            onValueChanged = {newAmount ->
                                amount = if (newAmount.isEmpty()){
                                    0
                                }else{
                                    newAmount.toInt()
                                }
                                val bd = BigDecimal(amount * conversionRate)
                                val roundoff = bd.setScale(2, RoundingMode.FLOOR)
                                total = roundoff.toDouble()
                            })
                        Spacer(Modifier.height(16.dp))
                        Text(text = "Yearly free remittances", color = BlueTextColor,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Remittances are free with Moneco until you reach your limit, which resets every year.", color = BlueTextColor,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_medium))
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Check number of free remittance remaining",
                            fontSize = 14.sp,
                            color = Blue2TextColor,
                            fontFamily = FontFamily(Font(R.font.outfit_medium)))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Fees breakdown", color = BlueTextColor,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        FeeRow(description = "Moneco fees", amount = "0.0 EUR")
                        Spacer(modifier = Modifier.height(6.dp))
                        FeeRow(description = "Transfer fee", amount = "0.0 EUR")
                        Spacer(modifier = Modifier.height(6.dp))
                        FeeRow(description = "Conversion rate", amount = "$conversionRate XOF")
                        Spacer(modifier = Modifier.height(6.dp))
                        FeeRow(description = "You’ll spend in total", amount = "0.0 EUR")
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "Recipient gets", color = GrayIcon,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.outfit_medium)))
                            Text(text = "$total XOF", color = BlueTextColor,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.outfit_medium)))
                        }
                    }
                    if(scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f)) // Adjust scrim color and transparency
                        )
                    }
                }
            }
        }
    }
}

private fun checkAmount(amount: Int, balance: Double?): Boolean {
    balance?.let {
        return amount > 0 && it >= amount
    } ?: return false
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountCard(amount: Int, sharedPreferences: SharedPreferences, onValueChanged: (String) -> Unit) {

    Card (
        border = BorderStroke(
            width = 1.dp,
            color = if (checkAmount(amount, sharedPreferences.userInfo?.balance)) GreenCustom else GrayIcon.copy(alpha = 0.7f)
        ),
        colors = CardDefaults.cardColors(
          containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(5), color = Color.White)
    ){
        Column{
            Row(
                Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(value = amount.toString(),
                    onValueChange = { amount -> onValueChanged(amount)},
                    textStyle = TextStyle(
                        color = BlueTextColor,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.outfit_semi_bold))
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent) // No background
                        .padding(0.dp)
                        .weight(4f), // No padding
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent, // No background
                        focusedIndicatorColor = Color.Transparent, // Remove the bottom line when focused
                        unfocusedIndicatorColor = Color.Transparent, // Remove the bottom line when unfocused
                        cursorColor = Color.Black // Set the cursor color
                    ),
                    singleLine = true
                )
                Text(text = "EUR", color = GrayIcon,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Box (
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (checkAmount(
                                amount,
                                sharedPreferences.userInfo?.balance
                            )
                        ) GreenCustom.copy(alpha = 0.2f) else GrayBackground
                    )
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = if (checkAmount(
                                amount,
                                sharedPreferences.userInfo?.balance
                            )
                        ) GreenCustom else GrayIcon.copy(alpha = 0.7f)
                    )
            ){
                Text(text = "Your current balance is ${sharedPreferences.userInfo?.balance} EUR",
                    color = BlueTextColor,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_medium)),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp))
            }
        }
    }
}

@Composable
fun BottomSheet(
    context: Context,
    name: String?,
    amount: Double?,
    walletName: String?,
    phone: String
){
   Column (
       Modifier
           .fillMaxWidth()
           .padding(16.dp)
   ){
       Text(text = "Confirm transfer",
           color = BlueTextColor,
           fontSize = 24.sp,
           fontFamily = FontFamily(Font(R.font.outfit_semi_bold)))
       Spacer(modifier = Modifier.height(12.dp))
       Text(text = "You're sending",
           color = GrayIcon,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.outfit_medium)))
       Spacer(modifier = Modifier.height(6.dp))
       Text(text = "$amount XOF",
           color = BlueTextColor,
           fontSize = 18.sp,
           fontFamily = FontFamily(Font(R.font.outfit_semi_bold)))
       Spacer(modifier = Modifier.height(12.dp))
       Text(text = "To",
           color = GrayIcon,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.outfit_medium)))
       Spacer(modifier = Modifier.height(6.dp))
       name?.let {
           Text(text = it,
               color = BlueTextColor,
               fontSize = 18.sp,
               fontFamily = FontFamily(Font(R.font.outfit_semi_bold)))
       }
       Spacer(modifier = Modifier.height(12.dp))
       Text(text = "Via",
           color = GrayIcon,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.outfit_medium)))
       Spacer(modifier = Modifier.height(6.dp))
       Text(text = "$walletName : $phone",
           color = BlueTextColor,
           fontSize = 18.sp,
           fontFamily = FontFamily(Font(R.font.outfit_semi_bold)))
       Spacer(modifier = Modifier.height(24.dp))
       Button(
           onClick = {
               val intent = Intent(context, SuccessActivity::class.java)
               context.startActivity(intent)
           },
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00754E))
       ) {
           Text(text = "Confirm", color = Color.White)
       }
   }
}

@Composable
fun FeeRow(description: String, amount: String){
    Row (
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = description, color = GrayIcon,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.outfit_medium)))
        Text(text = amount, color = BlueTextColor,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.outfit_medium)))
    }
}