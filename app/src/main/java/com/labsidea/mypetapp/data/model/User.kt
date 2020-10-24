package com.labsidea.mypetapp.data.model

data class User(
    val pk: Int = 0,
    val name: String = "",
    val description: String = "",
    val abbreviation: String  = "",
    val email: String = "",
    val phone: String  = "",
    val website: String = "",
    val socialNetwork: String  = "",
    val president: String = "" ,
    val logo: String = "",
    val city: String = "",
    val is_deleted: Int = 0
)