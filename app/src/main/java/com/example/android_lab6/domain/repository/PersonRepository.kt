package com.example.android_lab6.domain.repository

import com.example.android_lab6.data.entity.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersons(): Flow<List<Person>>

    suspend fun getFilteredPersons(text: String): List<Person>

    suspend fun addPerson(person: Person)

    suspend fun countPersons(): Flow<Int>

    suspend fun countFilteredPersons(text: String): Int
}