package com.example.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private var index: Int = 0
    private var size: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val array = resources.getStringArray(R.array.picture_descriptions)
        size = array.size
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun updatePicture(index: Int) {
        val fm: FragmentManager = this.supportFragmentManager
        var fragment: Fragment2 = fm.findFragmentById(R.id.fragment2) as Fragment2
        fragment.Fragment2()
        fragment.setText(this.findViewById(R.id.fragment2), index)
        fragment.setImage(this.findViewById(R.id.fragment2), index)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        when (id) {
            R.id.menu_previous -> {
                if (index == 0) {
                    index = size-1
                } else {
                    index -= 1
                }
                updatePicture(index)
                return true
            }
            R.id.menu_next -> {
                if (index == size-1) {
                    index = 0
                } else {
                    index += 1
                }
                updatePicture(index)
                return true
            }
            android.R.id.home, R.id.menu_exit -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}