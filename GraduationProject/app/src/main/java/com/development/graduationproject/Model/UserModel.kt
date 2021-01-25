package com.development.graduationproject.Model

data class UserModel(
    var id: Int = 0,
    var name: String = "",
    var surname: String = "",
    var username: String = "",
    var password: String = "",
    var mail: String = "",
    var userType: String = ""
)