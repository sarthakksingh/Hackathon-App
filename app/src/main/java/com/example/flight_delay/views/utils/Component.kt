package com.example.flight_delay.views.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight_delay.views.screens.OnboardingScreen

@Composable
fun DelayResultCard(
    prediction: String,
    probability: Float
) {
    val isDelayed = prediction == "Delayed"
    val cardColor = if (isDelayed) Color(0xFFFFE5E5) else Color(0xFFE6F4EA)
    val textColor = if (isDelayed) Color(0xFFD32F2F) else Color(0xFF2E7D32)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = prediction,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Chance of delay: ${(probability * 100).toInt()}%",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Prediction based on historical data, route congestion and time of departure.",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DelayCardPreview() {

    DelayResultCard (
        prediction = "Delayed",
        probability = 0.82f
    )
}