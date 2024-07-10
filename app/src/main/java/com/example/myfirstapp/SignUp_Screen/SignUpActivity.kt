package com.example.myfirstapp.SignUp_Screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.ButtonNavigationBar_Screen.MainActivityButtomnavScreen
import com.example.myfirstapp.R
import com.example.myfirstapp.SignIn_Screen.SignInActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var btnSignUp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //initalize the button id
        val backToSignIn = findViewById<Button>(R.id.backToSignIn)
        btnSignUp = findViewById(R.id.btnSignUp)

        //go back to SignIn screen
        backToSignIn.setOnClickListener({
            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)
        })

        //go to MainActivity
        btnSignUp.setOnClickListener({
            val intentMainActivity = Intent(this@SignUpActivity, MainActivityButtomnavScreen::class.java)
            startActivity(intentMainActivity)
        })
    }
}