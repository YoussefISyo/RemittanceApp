package com.isyo.remitconnecct.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isyo.remitconnecct.R
import com.isyo.remitconnecct.ui.theme.BlueTextColor
import com.isyo.remitconnecct.ui.theme.GrayBackground
import com.isyo.remitconnecct.ui.theme.GrayIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier : Modifier = Modifier,
    text : String,
    onTextChange : (String) -> Unit,
    placeHolder : String,
    onCloseClicked : () -> Unit,
    onSearchClicked : (String) -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(
                    0.1.dp,
                    SolidColor(GrayBackground.copy(alpha = 0.5f))
                ),
                RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = GrayBackground),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = GrayIcon,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_regular)),
                )
            },
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = GrayIcon,
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotBlank()){
                        onCloseClicked()
                    }
                }) {
                    if (text.isNotBlank()){
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = BlueTextColor,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = BlueTextColor,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.outfit_medium)),

                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = GrayBackground,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black

            ),
        )

    }
}