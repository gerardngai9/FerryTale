package com.example.myapplication

class User (
    val fullName: String,
    val phone: String,
    val birthDay: String,
    val username: String,
    val userRole: String
)
{
    constructor(): this("","","", "", "" )
}