package com.example.myfirstapp.ButtonNavigationBar_Screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myfirstapp.Author_Screen.AuthorFragment
import com.example.myfirstapp.Cart_Screen.CartFragment
import com.example.myfirstapp.Search_Screen.SearchFragment
import com.example.myfirstapp.Home_Screen.HomeFragment
import com.example.myfirstapp.R
import com.example.myfirstapp.Setting_Screen.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityButtomnavScreen : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_buttomnav_screen)

        bottomNavigationView = findViewById(R.id.bottomNaviView)
        frameLayout = findViewById(R.id.frameLayout)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> loadFragment(HomeFragment(), false)
                R.id.navCart -> loadFragment(CartFragment(), false)
                R.id.navSearch -> loadFragment(AuthorFragment(), false)
                R.id.navNotification -> loadFragment(SettingFragment(), false)
                else -> loadFragment(SearchFragment(), false)
            }
            true
        }

        loadFragment(HomeFragment(), true)
    }

    private fun loadFragment(fragment: Fragment, isAppInitialized: Boolean) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment)
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}
