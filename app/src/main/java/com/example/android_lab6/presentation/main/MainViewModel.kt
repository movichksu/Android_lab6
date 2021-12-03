package com.example.android_lab6.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_lab6.Dependencies
import com.example.android_lab6.data.entity.Person
import com.example.android_lab6.domain.repository.PersonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val personRepository: PersonRepository by lazy { Dependencies.getPersonsRepository() }
    val persons = MutableLiveData<List<Person>>(listOf())
    val countPersons = MutableLiveData<Int>()
    val countFilteredPersons = MutableLiveData<Int>()

    fun onLaunch() {
        viewModelScope.launch {
            personRepository.getPersons().collect {
                persons.value = it
            }
        }
        viewModelScope.launch {
            personRepository.countPersons().collect {
                countPersons.value = it
            }
        }
    }

    fun onSearchFilteredPersons(text: String) {
        viewModelScope.launch {
            countFilteredPersons.value =
                if (text.isNotBlank()) personRepository.countFilteredPersons(text)
                else 0
            persons.value = personRepository.getFilteredPersons(text)
        }
    }
}