package com.normanhoeller.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.normanhoeller.tictactoe.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = MainFragment.createInstance()
            supportFragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()
        }
    }
}
