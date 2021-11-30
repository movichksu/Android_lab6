package com.example.android_lab6.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["name", "surname"])
data class Person(
    val name: String,
    val surname: String,
    val comment: String
)