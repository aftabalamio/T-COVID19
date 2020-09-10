package com.mdaftabalam.covid19.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mdaftabalam.covid19.R
import kotlinx.android.synthetic.main.activity_main.*
import com.mdaftabalam.covid19.fragment.AnalysisFragment
import com.mdaftabalam.covid19.fragment.GlobalFragment
import com.mdaftabalam.covid19.fragment.IndiaFragment
import com.mdaftabalam.covid19.fragment.InfoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //title = resources.getString(R.string.nav_home)
        loadFragment(AnalysisFragment())

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_analysis -> {
                    //title = resources.getString(R.string.nav_home)
                    loadFragment(AnalysisFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_global -> {
                    //title = resources.getString(R.string.nav_home)
                    loadFragment(GlobalFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_india -> {
                    //title = resources.getString(R.string.nav_books)
                    loadFragment(IndiaFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_info -> {
                    //title = resources.getString(R.string.nav_books)
                    loadFragment(InfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}