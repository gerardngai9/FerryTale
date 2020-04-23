package com.example.myapplication

class Booking (
    val bookingID: String,
    val userID: String,
    val origin: String,
    val destination: String,
    val startTime: String,
    val seat: Int,
    val total: Double
)
{
    constructor(): this("","","", "", "",0, 0.00)
}