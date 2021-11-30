package com.example.android_lab6.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_lab6.data.entity.Person

@Database(entities = [Person::class], version = 1)
abstract class PersonsDatabase: RoomDatabase() {

    abstract fun getPersonsDao(): PersonsDao
}