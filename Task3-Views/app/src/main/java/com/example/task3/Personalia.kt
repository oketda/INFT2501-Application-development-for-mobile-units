package com.example.task3

import java.io.Serializable
import java.util.*

class Personalia(name: String?, dateOfBirth: Calendar): Serializable {
    var name = name
    var dateOfBirth = dateOfBirth
}