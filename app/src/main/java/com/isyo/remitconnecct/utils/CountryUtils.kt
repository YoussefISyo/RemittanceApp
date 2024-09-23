package com.isyo.remitconnecct.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.isyo.remitconnecct.R

object CountryUtils{

    enum class Countries(
        @DrawableRes val flagImageResId: Int,
        val countryNameStringId: String,
        val countryCode: String
    ){
        Algeria(
            flagImageResId = R.drawable.flag_dz,
            countryNameStringId = "Algeria",
            countryCode = "+213"
        ),
        France(
            flagImageResId = R.drawable.flag_fr,
            countryNameStringId = "France",
            countryCode = "+33"
        ),
        Benin(
            flagImageResId = R.drawable.flag_bj,
            countryNameStringId = "Benin",
            countryCode = "+229"
        )
    }
}