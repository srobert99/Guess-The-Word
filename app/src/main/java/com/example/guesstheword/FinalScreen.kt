package com.example.guesstheword

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FinalScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)
        finalScreen()
    }


    private fun finalScreen(){
        val status = intent.getStringExtra("status")
        findViewById<TextView>(R.id.statusTV).setText(status)
    }
}