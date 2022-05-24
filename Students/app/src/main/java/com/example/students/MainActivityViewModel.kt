package com.example.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val appDataBase = AppDataBase.getInstance(application.applicationContext)
    private val dao = appDataBase.personDao()

    var persons = MutableLiveData<List<PersonModel>>()

    fun getAllPersons() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = dao.getAllPersons()
            persons.postValue(result)
        }
    }

    fun insertPerson(personModel: PersonModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertPerson(personModel)
            getAllPersons()
        }
    }

    fun findByName(string: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = dao.findByName(string)
            persons.postValue(result)
        }
    }
}