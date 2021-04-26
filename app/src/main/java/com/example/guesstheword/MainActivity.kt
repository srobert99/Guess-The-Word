package com.example.guesstheword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var lives:Int=3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val answer=generateWord()
        generateWordAspect(answer)

        val goButton=findViewById<Button>(R.id.startButton)
        goButton.setOnClickListener {
            guessLetter(answer)
        }

    }

    private fun generateWord():String{
        val words=listOf("CARTE","CREIER","EXTRATERESTRU","ELEFANT","NAVETA","ENCICLOPEDIE","TELEENCICLOPEDIE")
        val word=words[(0..words.size-1).random()]
        return word
    }

    private fun generateWordAspect(word:String){
        var wordAux=word
        for(i in 1..3) {
            val letter: Char = word.random()
            wordAux = wordAux.replace(letter, '_')
        }
        findViewById<TextView>(R.id.wordTV).setText(wordAux)
    }

    private fun guessLetter(word: String) {
        if(findViewById<EditText>(R.id.letterET).text.toString().isNotEmpty()) {
            val letter = findViewById<EditText>(R.id.letterET).text.toString()[0].toLowerCase()
            var guessText = findViewById<TextView>(R.id.wordTV).text.toString().toCharArray()
            var usedLetters = findViewById<TextView>(R.id.usedLettersTV).text.toString()
            val length = (word.length) - 1
            var ok = 0
            for (c in 0..length) {
                if (word[c].toLowerCase() == letter && guessText[c] == '_') {
                    guessText[c] = letter.toUpperCase()
                    ok = 1
                }
            }
            if (ok == 0) {
                lives--
            }
            findViewById<EditText>(R.id.letterET).text.clear()
            findViewById<TextView>(R.id.usedLettersTV).text = usedLetters + letter + " "
            findViewById<TextView>(R.id.wordTV).text = String(guessText)
            findViewById<TextView>(R.id.livesTV).text = "Lives: " + (lives.toString())
            endGame(word)
        }

    }

    private fun endGame(word:String){
        if(lives==0){
            val intent=Intent(this,FinalScreen::class.java)
            intent.putExtra("status","YOU LOSE")
            startActivity(intent)
            finish()
        }
        val guessedWord=findViewById<TextView>(R.id.wordTV).text.toString()
        if(guessedWord.equals(word)){
            val intent=Intent(this,FinalScreen::class.java)
            intent.putExtra("status","YOU WIN")
            startActivity(intent)
            finish()
        }

    }

}