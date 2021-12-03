package com.example.android_lab6.domain.repository.impl

import android.content.Context
import androidx.room.Room
import com.example.android_lab6.data.database.PersonsDatabase
import com.example.android_lab6.data.entity.Person
import com.example.android_lab6.domain.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PersonRepositoryImpl(context: Context): PersonRepository {

    private val database = Room.databaseBuilder(
        context,
        PersonsDatabase::class.java,
        "personsDatabase"
    ).build()

    override fun getPersons(): Flow<List<Person>> =
        database.getPersonsDao().selectAll()

    override suspend fun getFilteredPersons(text: String): List<Person> =
        withContext(Dispatchers.IO) {
            database.getPersonsDao().getFilteredPersons(text)
        }

    override suspend fun addPerson(person: Person) {
        withContext(Dispatchers.IO) {
            database.getPersonsDao().insert(person)
        }
    }

    override suspend fun countPersons(): Flow<Int> =
            database.getPersonsDao().countAllRows()

    override suspend fun countFilteredPersons(text: String): Int =
        withContext(Dispatchers.IO) {
            database.getPersonsDao().countFilteredRows(text)
        }
}