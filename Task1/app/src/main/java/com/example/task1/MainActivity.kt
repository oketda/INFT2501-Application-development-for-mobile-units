package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(meny: Menu): Boolean{
        super.onCreateOptionsMenu(meny)
        meny.add("Herman Tolpinrud")
        meny.add("Aagaard")
        Log.d("INFT2501","menu created")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item.getTitle().equals("Herman Tolpinrud")){
            Log.w("INFT2501 warining","Herman Tolpinrud is selected by the user")
        }
        if (item.getTitle().equals("Aagaard")){
            Log.e("INFT2501 error", "Aagaard is selected by the user")
        }
        return true
    }
}