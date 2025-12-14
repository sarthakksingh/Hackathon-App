package com.example.flight_delay.data.model


data class Response(
    val origin: String,
    val destination: String,
    val predictions: List<Prediction>,

)

data class Prediction(
    val airline: String,
    val prediction: String,
    val predicted_delay_minutes: Double,
    val delay_probability_percent: Double
)

