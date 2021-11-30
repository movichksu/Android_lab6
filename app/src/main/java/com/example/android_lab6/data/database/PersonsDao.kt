package com.example.android_lab6.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_lab6.data.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonsDao {

    @Query("SELECT * FROM Person")
    fun selectAll(): Flow<List<Person>>

    @Query("SELECT * FROM Person WHERE UPPER(name) LIKE '%' || :text || '%' OR UPPER(surname) LIKE '%' || :text || '%'")
    fun getFilteredPersons(text: String): Flow<List<Person>>

    @Query("SELECT COUNT(*) FROM Person")
    fun countAllRows(): Int

    @Query("SELECT COUNT(*) FROM Person WHERE UPPER(name) LIKE '%' || :text || '%' OR UPPER(surname) LIKE '%' || :text || '%'")
    fun countFilteredRows(text: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)
}