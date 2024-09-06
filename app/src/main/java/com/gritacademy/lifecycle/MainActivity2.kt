package com.gritacademy.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity2 : AppCompatActivity() {

    lateinit var tv:TextView
    lateinit var nameEdit:EditText
    lateinit var phoneEdit:EditText
    lateinit var emailEdit:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        nameEdit = findViewById(R.id.editTextName)
        phoneEdit = findViewById(R.id.editTextEmail)
        emailEdit = findViewById(R.id.editTextNumber)

        val btn = findViewById<Button>(R.id.button2)
        val backBtn = findViewById<Button>(R.id.button3)
        tv = findViewById(R.id.textView)


        //Set up for spinner
        val gender = arrayOf("Male", "Female", "Other", "Rather not choose")
        val spinner = findViewById<View>(R.id.spinner) as Spinner
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            gender)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        //update the text from input and update information from spinner
        btn.setOnClickListener{
            upDateText()

            val spinnerText = spinner.selectedItem.toString()
            Log.i("sami", "spinnertext " + spinnerText)
        }

        //option for go back
        backBtn.setOnClickListener {
            val a2 = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(a2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //put the input text into a textview with a reminder if empty or welcome + input
    fun upDateText() {

        if (nameEdit.text.toString().isEmpty()){
            tv.text = "Please enter a name"
        } else{
            tv.text = "Welcome " + nameEdit.text.toString()
        }
    }
}