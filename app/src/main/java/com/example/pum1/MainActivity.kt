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

        // Initialize RecyclerView
        dogRecyclerView = findViewById(R.id.dogRecyclerView)
        dogRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the ViewModel
        dogsViewModel = ViewModelProvider(this).get(DogsViewModel::class.java)

        // Initialize the Adapter
        dogAdapter = DogAdapter(this, emptyList()) // Start with an empty list
        dogRecyclerView.adapter = dogAdapter

        // Observe the LiveData from ViewModel
        dogsViewModel.dogs.observe(this, { dogList ->
            // Update the list when data changes
            dogAdapter = DogAdapter(this, dogList)
            dogRecyclerView.adapter = dogAdapter
        })

        // Get the reference to the button and set a click listener
        addDogButton = findViewById(R.id.addDogButton)
        addDogButton.setOnClickListener {
            showAddDogDialog()
        }
    }

    private fun showAddDogDialog() {
        // Create an EditText for the dog name
        val dogNameInput = EditText(this)
        dogNameInput.hint = "Enter Dog Breed Name"

        // Create a layout for the dialog
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 16, 32, 16)
        layout.addView(dogNameInput)
        // Create and show the dialog
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

