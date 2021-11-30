package com.example.android_lab6.domain.interactor

import com.example.android_lab6.data.entity.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun getPersons(): Flow<List<Person>>

    fun getFilteredPersons(text: String): Flow<List<Person>>

    suspend fun addPerson(person: Person)

    suspend fun countPersons(): Int

    suspend fun countFilteredPersons(text: String): Int
}