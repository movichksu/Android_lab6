package com.example.android_lab6

import com.example.android_lab6.domain.repository.PersonRepository
import com.example.android_lab6.domain.repository.impl.PersonRepositoryImpl

object Dependencies {

    private val personRepository: PersonRepository by lazy {PersonRepositoryImpl(App.instance)}

    fun getPersonsRepository(): PersonRepository = personRepository
}