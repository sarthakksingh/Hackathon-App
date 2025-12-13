package com.example.flight_delay.views.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight_delay.R
import com.example.flight_delay.data.model.InputUsed
import com.example.flight_delay.data.model.Response

@Composable
fun SearchResponseCard(
    response: Response
) {
    val probabilityPercent = (response.probability * 100).toInt()
    val isDelayed = response.prediction.equals("Delayed", true)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp), // ⬅️ Taller card
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB091D5)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // BIG airline logo
            Image(
                painter = painterResource(airlineLogo(response.input_used.airline)),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Route text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(response.input_used.origin, fontWeight = FontWeight.Bold)
                Text(response.input_used.destination, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(6.dp))

            // Small airplane route
            Image(
                painter = painterResource(R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp),
                contentScale = ContentScale.FillWidth
            )

            Spacer(Modifier.height(10.dp))

            // Perforated dashed line
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.dash_line),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )

                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color(0xFF310581), CircleShape)
                        .align(Alignment.CenterStart)
                        .offset(x = (-8).dp)
                )

                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color(0xFF310581), CircleShape)
                        .align(Alignment.CenterEnd)
                        .offset(x = 8.dp)
                )
            }

            Spacer(Modifier.height(14.dp))

            // Bottom row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFFAC01B).copy(alpha = 0.25f),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        response.prediction,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xFFFAC01B)
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    "$probabilityPercent%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFFF8D503)
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResponseCardPreview() {

    SearchResponseCard(
        response = Response(
            prediction = "Delayed",
            probability = 0.78,
            input_used = InputUsed(
                airline = "IndiGo",
                origin = "DEL",
                destination = "BOM"
            )
        )
    )
}