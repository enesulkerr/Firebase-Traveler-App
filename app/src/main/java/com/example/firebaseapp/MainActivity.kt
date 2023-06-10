package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseapp.screen.HomeScreen
import com.example.firebaseapp.service.FirebaseAuthManager

class MainActivity : AppCompatActivity() {

    lateinit var girisbtn : Button
    lateinit var yenikayitbtn : Button
    lateinit var emailtext : EditText
    lateinit var passwordtext : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        girisbtn = findViewById(R.id.girisbtn)
        yenikayitbtn = findViewById(R.id.yenikayıtbtn)
        emailtext = findViewById(R.id.emailedittext)
        passwordtext = findViewById(R.id.passwordedittext)
        passwordtext.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD


        yenikayitbtn.setOnClickListener {
            val email = emailtext.text.toString()
            val password = passwordtext.text.toString()
            val authManager = FirebaseAuthManager()

            if (email.isNotEmpty() || password.isNotEmpty()) {
                authManager.registerUser(email, password) { success, message ->
                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT)
                    .show()}
        }


        girisbtn.setOnClickListener {
            val email = emailtext.text.toString()
            val password = passwordtext.text.toString()
            val authManager = FirebaseAuthManager()

            if (email.isNotEmpty() || password.isNotEmpty()) {
                authManager.loginUser(email, password){ success, message ->
                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeScreen::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
                    }}}
            else{
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }






    }
