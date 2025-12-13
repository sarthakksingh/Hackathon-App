package com.example.flight_delay.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.flight_delay.views.utils.SearchInputCard
import com.example.flight_delay.views.utils.SearchResponseCard

@Composable
fun SearchScreen(
    onBackClick: () -> Unit
) {


    val mockResponse = Response(
        prediction = "Delayed",
        probability = 0.78,
        input_used = InputUsed(
            airline = "IndiGo",
            origin = "DEL",
            destination = "BOM"
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF310581))
            .padding(horizontal = 16.dp, vertical = 36.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBackClick() }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Search Result",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Image(
                painter = painterResource(id = R.drawable.world),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            SearchInputCard()

            Spacer(modifier = Modifier.height(20.dp))


            SearchResponseCard(response = mockResponse)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(onBackClick = {})
}
