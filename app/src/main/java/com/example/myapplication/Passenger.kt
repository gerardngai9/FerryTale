package com.example.myapplication

class Passenger (
    val uid: String,
    val emailID: String,
    val phoneNum: String,
    val PrimName: String,
    val PrimAge: String,
    val CoName: String,
    val CoAge: String
)
{
    constructor(): this("","","", "", "", "","")
}