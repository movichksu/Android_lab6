package com.example.android_lab6.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_lab6.Dependencies
import com.example.android_lab6.data.entity.Person
import com.example.android_lab6.domain.interactor.PersonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val personRepository: PersonRepository by lazy { Dependencies.getPersonsRepository() }
    val persons = MutableLiveData<List<Person>>(listOf())
    val countPersons = MutableLiveData<Int>()
    val countFilteredPersons = MutableLiveData<Int>()

    fun onLaunch() {
        onCountTotalPersons()
        viewModelScope.launch {
            personRepository.getPersons().collect {
                persons.value = it
            }
        }
    }

    fun onCountTotalPersons() {
        viewModelScope.launch {
            countPersons.value = personRepository.countPersons()
        }
    }

    fun onSearchFilteredPersons(text: String) {
        viewModelScope.launch {
            countFilteredPersons.value =
                if (text.isNotBlank()) personRepository.countFilteredPersons(text)
                else 0
            personRepository.getFilteredPersons(text).collect {
                persons.value = it
            }
        }
    }
}