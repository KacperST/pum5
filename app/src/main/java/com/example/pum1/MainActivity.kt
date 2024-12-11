package com.example.pum1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var dogRecyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var addDogButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogRecyclerView = findViewById(R.id.dogRecyclerView)
        dogRecyclerView.layoutManager = LinearLayoutManager(this)

        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]

        dogAdapter = DogAdapter(this, emptyList())
        dogRecyclerView.adapter = dogAdapter

        dogsViewModel.dogs.observe(this, { dogList ->
            dogAdapter = DogAdapter(this, dogList)
            dogRecyclerView.adapter = dogAdapter
        })

        TODO("Set a click listener on button and invoke dialog")
    }
    // DON'T TOUCH
    private fun showAddDogDialog() {
        val dogNameInput = EditText(this)
        dogNameInput.hint = "Enter Dog Breed Name"
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 16, 32, 16)
        layout.addView(dogNameInput)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Dog Breed")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val dogName = dogNameInput.text.toString()

                if (dogName.isNotEmpty()) {
                    val newDog = DogBreed(dogName, dogName)
                    dogsViewModel.addDogBreed(newDog)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
}

