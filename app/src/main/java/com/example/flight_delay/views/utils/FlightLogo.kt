package com.example.flight_delay.views.utils

import androidx.compose.runtime.Composable
import com.example.flight_delay.R

@Composable
fun airlineLogo(airline: String): Int {
    return when (airline.lowercase()) {
        "indigo" -> R.drawable.indigo
        "air india" -> R.drawable.air_india
        "vistara" -> R.drawable.vistara
        "spicejet" -> R.drawable.spice_jet_logo
        "akasa air" -> R.drawable.akasa_air_logo
        else -> R.drawable.from_ic
    }
}
