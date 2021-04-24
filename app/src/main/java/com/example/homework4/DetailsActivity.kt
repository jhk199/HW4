package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class DetailsActivity : AppCompatActivity() {
    // All my variables
    private lateinit var textViewName: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewContents: TextView
    private lateinit var textViewReflection: TextView
    private lateinit var textViewEmotion: TextView
    // Buttons
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button

    // Variables for intent
    private lateinit var idIntent:String

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        textViewName = findViewById(R.id.textViewDetails_name)
        textViewDate = findViewById(R.id.textViewDetails_dataDate)
        textViewContents = findViewById(R.id.textViewDetails_dataContents)
        textViewReflection = findViewById(R.id.textViewDetails_dataReflection)
        textViewEmotion = findViewById(R.id.textViewDetails_dataEmotion)
        buttonUpdate = findViewById(R.id.button_update)
        buttonDelete = findViewById(R.id.button_remove)

        // intent
        val intent = intent.getStringExtra("id") as String
        val id = Integer.parseInt(intent)

        dreamViewModel.select(id).observe(this, Observer {
            if (it != null) {
                textViewName.text = it.name
                textViewDate.text = it.date
                textViewContents.text = it.contents
                textViewReflection.text = it.reflection
                textViewEmotion.text = it.emotion
                idIntent = it.id.toString()
            }
        })

        buttonDelete.setOnClickListener{
            dreamViewModel.deleteByDream(id)
            finish()
        }

        buttonUpdate.setOnClickListener {
            val intentNext = Intent(this@DetailsActivity, UpdateActivity::class.java)
            intentNext.putExtra("id", idIntent)
            intentNext.putExtra("name", textViewName.text)
            intentNext.putExtra("date", textViewDate.text)
            intentNext.putExtra("contents", textViewContents.text)
            intentNext.putExtra("reflection", textViewReflection.text)
            intentNext.putExtra("emotion", textViewEmotion.text)
            startActivity(intentNext)

        }

    }
}