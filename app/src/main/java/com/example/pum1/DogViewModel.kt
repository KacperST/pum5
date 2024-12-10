package com.example.pum1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogsViewModel : ViewModel() {

    private val _dogs = MutableLiveData<List<DogBreed>>()
    val dogs: LiveData<List<DogBreed>> get() = _dogs

    private val dogList = mutableListOf<DogBreed>()

    init {
        // Initialize with some data
        dogList.add(DogBreed("Golden Retriever", "golden_retriever"))
        dogList.add(DogBreed("Labrador", "labrador"))
        dogList.add(DogBreed("Beagle", "beagle"))
        _dogs.value = dogList
    }

    // Method to add a new dog breed
    fun addDogBreed(dogBreed: DogBreed) {
        dogList.add(dogBreed)
        _dogs.value = dogList // Notify observers of the new data
    }
}