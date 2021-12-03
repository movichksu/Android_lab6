package com.example.android_lab6.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_lab6.Dependencies
import com.example.android_lab6.data.entity.Person
import com.example.android_lab6.domain.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormViewModel: ViewModel() {

    private val personRepository: PersonRepository by lazy { Dependencies.getPersonsRepository() }

    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.addPerson(person)
        }
    }
}