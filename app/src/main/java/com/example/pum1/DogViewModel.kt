package com.example.pum1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogsViewModel : ViewModel() {

    private val _dogs = MutableLiveData<List<DogBreed>>(mutableListOf())
    val dogs: LiveData<List<DogBreed>> get() = _dogs

    init {
        TODO("Initialize dogs with some examples. image names are in res/drawable")
    }

    fun todo(){
        TODO("IMplement functon to add new dogbreed called addDogBreed ")
    }

}