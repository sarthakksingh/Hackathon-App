package com.example.flight_delay.data.model


data class Response(
    val prediction: String,
    val probability: Double,
    val input_used: InputUsed
)

data class InputUsed(
    val airline: String,
    val origin: String,
    val destination: String
)

