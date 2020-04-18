package com.example.myapplication

class Customer (
    val userID: String,
    val fullName: String,
    val phone: String,
    val BirthDay: String,
    val username: String,
    val userRole: String
)
{
    constructor(): this( "","","", "", "","" )
}