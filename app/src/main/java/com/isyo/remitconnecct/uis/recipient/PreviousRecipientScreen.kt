package com.isyo.remitconnecct.uis.recipient

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.model.User
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.GrayIcon
import com.isyo.remitconnecct.uis.wallet.MobileWalletsActivity
import com.isyo.remitconnecct.uis.TransferViewModel
import com.isyo.remitconnecct.utils.components.MySearchBar

@Composable
fun PreviousRecipientScreen(
    recipientsUiState: RecipientsUiState,
    transferViewModel: TransferViewModel
) {
    var searchTerm by remember { mutableStateOf("") }
    var contactsList by remember { mutableStateOf(emptyList<User>()) }

    contactsList = listOf()
    Column (
        Modifier.fillMaxSize()
    ){
        MySearchBar(
            text = searchTerm,
            onTextChange = { searchTerm = it },
            placeHolder = "Search",
            onCloseClicked = { searchTerm = "" },
            onSearchClicked = {},
        )
        Spacer(modifier = Modifier.height(24.dp))
        when (recipientsUiState) {
            is RecipientsUiState.Loading -> LoadingScreen()
            is RecipientsUiState.Success -> {
                recipientsUiState.users.body()?.let {
                    contactsList = it
                }
                RecipientsList(contactsList, transferViewModel,
                    LocalContext.current, searchTerm)
            }
            is RecipientsUiState.Error -> Box{}
        }


    }
}

@Composable
fun LoadingScreen(){
    Box(Modifier.fillMaxSize()){
        //CircularProgressIndicator()
    }
}

@Composable
fun RecipientsList(
    contacts: List<User>,
    transferViewModel: TransferViewModel,
    context: Context,
    searchTerm: String,
) {
    val filteredContacts = contacts.filter { it.name.contains(searchTerm, ignoreCase = true) }
    if (filteredContacts.isNotEmpty()){
        Text("Contacts on your phone", color = BlueTextColor,
            fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        filteredContacts.forEachIndexed{ index, user ->
            Divider(color = GrayBackground)
            RecipientCard(name = user.name, transferViewModel, context)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Divider(color = GrayBackground)
    }else{
        Column (
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(painter = painterResource(id = R.drawable.icon_woman_ball),
                contentDescription = null, tint = Color.Unspecified)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "No matching results found !",
                color = BlueTextColor,
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                fontSize = 16.sp)
        }
    }
}

@Composable
fun RecipientCard(name: String, transferViewModel: TransferViewModel, context: Context){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .background(color = Color.White)
            .clickable {
                transferViewModel.updateName(name)
                transferViewModel.updatePhone("+229 98 767 289")
                val intent = Intent(context, MobileWalletsActivity::class.java)
                context.startActivity(intent)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row{
            Image(painter = painterResource(id = R.drawable.image_placeholder), contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Column (
                verticalArrangement = Arrangement.Center
            ){
                Text(name,
                    color = BlueTextColor,
                    fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                    fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "+229 98 767 289",
                    color = GrayIcon,
                    fontFamily = FontFamily(Font(R.font.outfit_medium)),
                    fontSize = 14.sp)
            }
        }
        Icon(painter = painterResource(id = R.drawable.icon_arrow), contentDescription = null)


    }
}