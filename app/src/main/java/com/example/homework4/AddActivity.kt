package com.example.homework4

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date

class AddActivity : AppCompatActivity() {

    private lateinit var editTextName : EditText
    private lateinit var editTextDate : EditText
    private lateinit var editTextContents : EditText
    private lateinit var editTextReflection : EditText
    private lateinit var selectSpinner : Spinner
    private lateinit var buttonSaveDream : Button
    var emotionArray = arrayOf("EMOTIONS", "fear", "panic", "loss of self", "grief",
        "freedom", "love", "joy", "vulnerability", "confused", "sad")

    private val dreamViewModel:DreamViewModel by viewModels {
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextName = findViewById(R.id.editText_name)
        editTextDate = findViewById(R.id.editText_date)
        editTextContents = findViewById(R.id.editText_contents)
        editTextReflection = findViewById(R.id.editText_reflection)
        selectSpinner = findViewById(R.id.select_spinner)
        buttonSaveDream = findViewById(R.id.button_saveDream)
        val searchSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, emotionArray)
        searchSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectSpinner.adapter = searchSpinner

        buttonSaveDream.setOnClickListener{
            if(TextUtils.isEmpty(editTextName.text) || TextUtils.isEmpty(editTextDate.text)
                                                    || TextUtils.isEmpty(editTextContents.text)
                                                    || TextUtils.isEmpty(editTextReflection.text)
                                                    || selectSpinner.selectedItem.toString() == "EMOTIONS") {
                toastError("Please fill in all of the fields to continue")
            }
            else {
                try {
                    val date = Date.valueOf(editTextDate.text.toString())
                    val dream = Dream(0, editTextName.text.toString(), date.toString(),
                        editTextContents.text.toString(), editTextReflection.text.toString(),
                        selectSpinner.selectedItem.toString())
                    dreamViewModel.insert(dream)
                    finish()
                }
                catch(e: Exception) {
                    toastError("Your date is wrong! Please make it YYYY-MM-DD!")
                }
            }
        }
    }

    private fun toastError(text:String) {
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    }
}