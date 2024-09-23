package com.isyo.remitconnecct.uis.recipient

import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayIcon
import com.isyo.remitconnecct.ui.theme.GreenCustom
import com.isyo.remitconnecct.uis.TransferViewModel
import com.isyo.remitconnecct.uis.wallet.MobileWalletsActivity
import com.isyo.remitconnecct.utils.CountryUtils

@Composable
fun NewRecipientScreen(transferViewModel: TransferViewModel) {
    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        bottomBar =
        {
            Button(
                enabled = phoneNumber.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty(),
                onClick = {
                    transferViewModel.updatePhone(phoneNumber)
                    transferViewModel.updateName("$firstName $lastName")
                    val intent = Intent(context, MobileWalletsActivity::class.java)
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
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Country",
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                modifier = Modifier.padding(start = 16.dp)
            )
            DropDownMenu()

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFFEFF8F7)) // Adjust as per the design
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = GreenCustom.copy(alpha = 0.1f))
                        .border(
                            width = 1.dp,
                            color = GreenCustom,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_document),
                        contentDescription = null,
                        tint = GreenCustom
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Choose a contact",
                        fontFamily = FontFamily(Font(R.font.outfit_regular)),
                        fontSize = 14.sp,
                        color = GreenCustom,
                    )
                }
            }

            // Divider with OR ADD MANUALLY
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = "OR ADD MANUALLY",
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.outfit_regular)),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Divider(modifier = Modifier.weight(1f))
            }

            // Phone Number
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Phone Number",
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 1.dp, color = GrayIcon.copy(alpha = 0.5f)),
            )

            // First Name
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "First name",
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 1.dp, color = GrayIcon.copy(alpha = 0.5f)),
            )

            // Last Name
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Last name",
                fontFamily = FontFamily(Font(R.font.outfit_semi_bold)),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(width = 1.dp, color = GrayIcon.copy(alpha = 0.5f)),
            )

            // Continue Button
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

}

@Composable
fun DropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("") }
    val countries = listOf(CountryUtils.Countries.Algeria, CountryUtils.Countries.Benin, CountryUtils.Countries.France)

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        OutlinedTextField(
            value = selectedCountry,
            onValueChange = { selectedCountry = it },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = GrayIcon.copy(alpha = 0.5f),)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
                .clickable {
                    expanded = !expanded
                },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            countries.forEach { country ->
                CountryItem(onClick = {
                    selectedCountry = country.countryNameStringId
                    expanded = false
                },
                    resIcon = country.flagImageResId,
                    countryCode = country.countryCode,
                    countryName = country.countryNameStringId)
            }
        }
    }

}

@Composable
fun CountryItem(countryName: String, resIcon: Int,
                countryCode: String, onClick: () -> Unit){
    Row(
        Modifier.clickable { onClick() }.height(40.dp).padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(painter = painterResource(id = resIcon), contentDescription = null,
                modifier = Modifier.clip(CircleShape),
                tint = Color.Unspecified)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = countryName, fontFamily = FontFamily(Font(R.font.outfit_regular)),
                color = BlueTextColor, fontSize = 14.sp)
        }
        Text(text = countryCode, fontFamily = FontFamily(Font(R.font.outfit_regular)),
            color = GrayIcon)
    }
}