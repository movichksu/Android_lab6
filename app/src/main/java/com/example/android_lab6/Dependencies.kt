package com.example.android_lab6

import com.example.android_lab6.domain.interactor.PersonRepository
import com.example.android_lab6.domain.interactor.impl.PersonRepositoryImpl

object Dependencies {

    private val personRepository: PersonRepository by lazy {PersonRepositoryImpl(App.instance)}

    fun getPersonsRepository(): PersonRepository = personRepository
}