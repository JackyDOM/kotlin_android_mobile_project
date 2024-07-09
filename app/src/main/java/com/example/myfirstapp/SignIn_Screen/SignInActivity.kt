package com.example.myfirstapp.SignIn_Screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R
import com.example.myfirstapp.SignUp_Screen.SignUpActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        //initialize the id of btnSignUp
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        //go to SignUp screen
        btnSignUp.setOnClickListener({
            //intent to SignUp Screen
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}