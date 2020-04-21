package com.example.myapplication

class Schedule (
    val scheduleID: String,
    val destination: String,
    val origin: String,
    val duration: String,
    val priceAdult: Double,
    val priceChild: Double,
    val startTime: String
)
{
    constructor(): this( "","","", "", 0.0, 0.0,"" )
}
