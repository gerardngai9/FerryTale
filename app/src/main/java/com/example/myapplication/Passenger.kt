package com.example.myapplication

class Passenger (
    val uid: String,
    val emailID: String,
    val phoneNum: String,
    val primName: String,
    val primAge: String,
    val coName: String,
    val coAge: String
)
{
    constructor(): this("","","", "", "", "","")
}