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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
    val isDelayed = response.prediction.equals("Delayed", ignoreCase = true)

    val screenBg = Color(0xFF310581)
    val cardBg = Color(0xFFB091D5)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(airlineLogo(response.input_used.airline)),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = response.input_used.origin,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = response.input_used.destination,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Spacer(Modifier.height(6.dp))


            Image(
                painter = painterResource(R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier
                    .height(28.dp),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(Color(0xFF310581))
            )

            Spacer(Modifier.height(12.dp))

            Image(
                painter = painterResource(R.drawable.dash_line),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(Color(0xFF310581))
            )




            Spacer(Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(
                    modifier = Modifier
                        .background(
                            if (isDelayed)
                                Color(0xFFFAC01B)
                            else
                                Color(0xFF11D244),
                            RoundedCornerShape(18.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = response.prediction,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }

                Spacer(Modifier.weight(1f))


                Text(
                    text = "$probabilityPercent%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFFEB3B)
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