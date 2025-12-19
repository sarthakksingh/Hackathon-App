package com.example.flight_delay.views.screens


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight_delay.R
import com.example.flight_delay.ui.state.UiStateHandler
import com.example.flight_delay.views.utils.SearchResponseCard
import com.example.flight_delay.vm.DelayVm

@Composable
fun SearchResultScreen(
    viewModel: DelayVm,
    onBackClick: () -> Unit
) {
    val state by viewModel.flightState.collectAsState()
    val scrollState = rememberScrollState()

    // Smooth image collapse based on scroll position
    val imageHeightFraction by animateFloatAsState(
        targetValue = (1f - (scrollState.value / 300f).coerceIn(0f, 1f)),
        label = "imageHeight"
    )

    Log.d("SearchResultScreen", "State: ${state.javaClass.simpleName}, Scroll: ${scrollState.value}, ImageHeight: ${imageHeightFraction}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF310581))
            .padding(horizontal = 16.dp, vertical = 36.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header (always visible)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBackClick() }
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Search Result",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(16.dp))

            // Collapsing World Image (smooth height + alpha animation)
            Image(
                painter = painterResource(id = R.drawable.world),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((200 * imageHeightFraction).dp)
                    .alpha(imageHeightFraction.coerceAtLeast(0.3f))
            )

            Spacer(modifier = Modifier.height((24 * imageHeightFraction).dp))

            UiStateHandler(
                modifier = Modifier.weight(1f),
                state = state,
                onSuccess = { response ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)  // Connected to scroll detection
                    ) {
                        response.predictions.forEachIndexed { index, prediction ->
                            SearchResponseCard(
                                origin = response.origin,
                                destination = response.destination,
                                prediction = prediction
                            )
                            if (index < response.predictions.size - 1) {
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                },
                onRetry = { viewModel.retryLastRequest() }
            )
        }
    }
}
