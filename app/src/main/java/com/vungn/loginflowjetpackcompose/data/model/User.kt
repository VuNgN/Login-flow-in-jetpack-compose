package com.vungn.loginflowjetpackcompose.data.model

data class User(
    val username: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val email: String,
    val token: String
)