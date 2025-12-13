package com.example.flight_delay.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight_delay.R

@Composable
fun OnboardingScreen(

) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp)
        ) {


            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {

                Text(
                    text = buildAnnotatedString {
                        append("Track your\n")
                        append("Dream ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFFFC107),
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Flight")
                        }
                        append("\nEasily")
                    },
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 64.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Find an easy way to track airplane delays\nwith just a few clicks in the application",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.75f),
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB04BFF)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {

        OnboardingScreen ()
}