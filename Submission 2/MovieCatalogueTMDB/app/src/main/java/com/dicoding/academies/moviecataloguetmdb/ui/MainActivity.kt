package com.dicoding.academies.moviecataloguetmdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.academies.moviecataloguetmdb.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f
    }
}