package com.example.dynamicfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview() {

        loadFragment(HomeFragment())

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId)
            {
                R.id.home ->
                {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.MyNetwork ->
                {
                    loadFragment(MyNetworkFragment())
                    true
                }
                R.id.Post ->
                {
                    loadFragment(PostFragment())
                    true
                }
                R.id.Notification ->
                {
                    loadFragment(NotificationFragment())
                    true
                }
                R.id.Jobs ->
                {
                    loadFragment(JobsFragment())
                    true
                }

                else -> {

                    println("NO Action")
                    true

                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}