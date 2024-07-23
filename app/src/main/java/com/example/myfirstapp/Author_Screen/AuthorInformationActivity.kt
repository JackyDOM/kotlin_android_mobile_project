package com.example.myfirstapp.Author_Screen

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myfirstapp.Adapters.AuthorAdapter
import com.example.myfirstapp.R
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthorInformationActivity : AppCompatActivity() {
    private val MAX_LINES_COLLAPSED = 2
    private var isExpanded = false
    private lateinit var authorAdapter: AuthorAdapter
    private var accessToken: String? = null
    private var authorName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_information)

        // Fetch access token from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        accessToken = sharedPreferences.getString("access_token", null)

        if (!accessToken.isNullOrEmpty()) {
            // Log the retrieved access token
            Log.d("AccessToken", "Retrieved Access Token: $accessToken")
        } else {
            // Handle scenario where access token is not available or empty
            Toast.makeText(this, "Access token not available or empty", Toast.LENGTH_LONG).show()
        }

        // Get intent extras
        val authorImageUrl = intent.getStringExtra("author_image_url")
        val authorGender = intent.getStringExtra("author_gender")
        authorName = intent.getStringExtra("author_name")
        val authorDesc = intent.getStringExtra("author_decs")

        // Set author image and details
        val imageView: ImageView = findViewById(R.id.imgAuthor)
        Picasso.get().load(authorImageUrl).into(imageView)

        val textViewGender: TextView = findViewById(R.id.txtAuthorGender)
        val textViewName: TextView = findViewById(R.id.txtAuthorName)
        val textViewDesc: TextView = findViewById(R.id.descriptionTextView)
        val showMoreTextView: TextView = findViewById(R.id.showMoreTextView)

        // Create and set styled text for author details
        setStyledText(textViewName, "Name: ", authorName)
        setStyledText(textViewGender, "Gender: ", authorGender)
        setStyledText(textViewDesc, "Description: ", authorDesc)
        textViewDesc.maxLines = MAX_LINES_COLLAPSED

        // Set click listener for "Show more" button
        showMoreTextView.setOnClickListener {
            if (isExpanded) {
                // Collapse the description
                textViewDesc.maxLines = MAX_LINES_COLLAPSED
                showMoreTextView.text = "Show more"
            } else {
                // Expand the description
                textViewDesc.maxLines = Integer.MAX_VALUE // Show all lines
                showMoreTextView.text = "Show less"
            }
            isExpanded = !isExpanded
        }
    }

    // Helper method to set styled text
    private fun setStyledText(textView: TextView, label: String, value: String?) {
        val spannableLabel = SpannableString(label)
        spannableLabel.setSpan(ForegroundColorSpan(Color.GREEN), 0, spannableLabel.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableLabel
        textView.append(value)
    }
}
