package com.example.flight_delay.data.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("origin")
    val origin:String,

    @SerializedName("destination")
    val destination: String,

    @SerializedName("timeDate")
    val timeDate: String
)
