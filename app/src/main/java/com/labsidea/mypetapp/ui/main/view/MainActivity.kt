package com.labsidea.mypetapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.labsidea.mypetapp.R
import com.labsidea.mypetapp.ui.animals.AnimalsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menuHome -> {
                supportFragmentManager.beginTransaction().add(R.id.frmMain, AnimalsFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menuEvents -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.menuProfile -> {
                //TODO
                return@OnNavigationItemSelectedListener true
            }
            R.id.menuOthers -> {

                //TODO
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().add(R.id.frmMain, AnimalsFragment()).commit()
    }
}

