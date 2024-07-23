package com.example.myfirstapp.Home_Screen

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myfirstapp.R
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {
    private val MAX_LINES_COLLAPSED = 5
    private var isExpanded = false
    private val addedBookIds = HashSet<Int>()
    private var isItemAddedToCart = false
    private lateinit var accessToken: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookPdf: String? = intent.getStringExtra("book_pdf")
        val addToFavoritesButton: Button = findViewById(R.id.btnAddToFavorites)

        //click and go to PDF screen
        addToFavoritesButton.setOnClickListener({
            // Log the value of bookPdf just before starting BookPdfActivity
            Log.d("BookDetailActivity", "PDF URL: " + bookPdf);

            // Create an Intent to navigate to FavoritesActivity
            var intentPDF = Intent(this@BookDetailActivity, BookPdfActivity::class.java)

            // Pass the book PDF URL to the BookPdfActivity
            intentPDF.putExtra("book_pdf_url", bookPdf)

            // Start the FavoritesActivity
            startActivity(intentPDF)
        })

        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val retrievedAccessToken = sharedPreferences.getString("access_token", null)

        if (retrievedAccessToken != null && retrievedAccessToken.isNotEmpty()) {
            Log.d("AccessToken", "Retrieved Access Token: $retrievedAccessToken")
            accessToken = retrievedAccessToken
        } else {
            Toast.makeText(this, "Access token not available or empty", Toast.LENGTH_LONG).show()
        }

        val bookImageUrl = intent.getStringExtra("book_image_url")
        val bookTitle = intent.getStringExtra("book_title")
        val bookPrice = intent.getStringExtra("book_price")
        val bookPublisher = intent.getStringExtra("book_publisher")
        val bookCategoryName = intent.getStringExtra("book_Category_Name")
        val authorName = intent.getStringExtra("author_name")
        val authorDecs = intent.getStringExtra("description")
        val bookId = intent.getIntExtra("book_id", 0)

        Log.d("SignIn", "Book ID: $bookId")
        Log.d("SignIn", "Book Pdf: $bookPdf")

        if ("free".equals(bookPrice, ignoreCase = true)) {
            findViewById<Button>(R.id.btnAddToFavorites).visibility = View.VISIBLE
        } else {
            findViewById<Button>(R.id.btnAddToCart).visibility = View.VISIBLE
        }

        val imageView = findViewById<ImageView>(R.id.BookImageView)
        val textView = findViewById<TextView>(R.id.txtTitleDetail)
        val textViewPrice = findViewById<TextView>(R.id.txtPriceCategoryGeneral)
        val textViewPublisher = findViewById<TextView>(R.id.txtpublisherCategoryGeneral)
        Picasso.get().load(bookImageUrl).into(imageView)

        val showMoreTextView = findViewById<TextView>(R.id.showMoreTextView)

        val spannableTitleLabel = SpannableString("Title: ").apply {
            setSpan(ForegroundColorSpan(Color.BLACK), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        textView.text = spannableTitleLabel
        textView.append(bookTitle)

        val spannablePriceLabel = SpannableString("Price: ").apply {
            setSpan(ForegroundColorSpan(Color.BLACK), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        textViewPrice.text = spannablePriceLabel
        textViewPrice.append(bookPrice)

        val spannablePublisherLabel = SpannableString("Publisher: ").apply {
            setSpan(ForegroundColorSpan(Color.BLACK), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        textViewPublisher.text = spannablePublisherLabel
        textViewPublisher.append(bookPublisher)

        val textViewBookCategoryName = findViewById<TextView>(R.id.BookCategoryName)
        textViewBookCategoryName.text = "Category Name: $bookCategoryName"

        val textViewAuthorName = findViewById<TextView>(R.id.BookAuthorName)
        textViewAuthorName.text = "Author Name: $authorName"

        val textViewAuthorDecs = findViewById<TextView>(R.id.BookAuthorDecs)
        textViewAuthorDecs.text = "Description: $authorDecs"
        textViewAuthorDecs.maxLines = MAX_LINES_COLLAPSED

        showMoreTextView.setOnClickListener {
            if (isExpanded) {
                textViewAuthorDecs.maxLines = MAX_LINES_COLLAPSED
                showMoreTextView.text = "Show more"
            } else {
                textViewAuthorDecs.maxLines = Integer.MAX_VALUE
                showMoreTextView.text = "Show less"
            }
            isExpanded = !isExpanded
        }
    }
}