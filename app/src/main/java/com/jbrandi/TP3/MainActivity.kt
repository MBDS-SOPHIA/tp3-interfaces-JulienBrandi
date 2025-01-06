package com.jbrandi.TP3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val textResult: TextView = findViewById(R.id.result)
        val textInput: TextView = findViewById(R.id.input)
        val inputValue = textInput.text.toString()
        
        if (inputValue.toInt() < 2 || inputValue.toInt() > 12) {
            textResult.text = "Enter a number between 2 and 12"
            return
        }

        val dice1 = Dice(6)
        val dice2 = Dice(6)

        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()
        val diceRollResults = diceRoll1 + diceRoll2
        val toast = Toast.makeText(this, "Dice Rolled", Toast.LENGTH_SHORT)
        toast.show()
        val textView: TextView = findViewById(R.id.textView1)
        textView.text = diceRoll1.toString()
        val textView2: TextView = findViewById(R.id.textView2)
        textView2.text = diceRoll2.toString()


        if (diceRollResults == inputValue.toInt()) {
            textResult.text = "Congrats! You win"
        } else {
            textResult.text = "Try again"
        }

    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}