package com.jbrandi.TP3

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
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

        val inputText: EditText = findViewById(R.id.input)
        inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                rollDice()
            }
        })
    }

    private fun rollDice() {
        val textResult: TextView = findViewById(R.id.result)
        val textInput: EditText = findViewById(R.id.input)
        val inputValue = textInput.text.toString().toIntOrNull()
        
        if (inputValue == null || inputValue < 2 || inputValue > 12) {
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


        if (diceRollResults == inputValue) {
            textResult.text = "Congrats! You win"
            animateDice(textView, textView2)
        } else {
            textResult.text = "Try again"
        }

    }

    private fun animateDice(dice1: TextView, dice2: TextView) {
        val animator1 = ObjectAnimator.ofFloat(dice1, "translationY", -100f, 0f)
        animator1.duration = 500
        animator1.repeatCount = 3
        animator1.repeatMode = ObjectAnimator.REVERSE

        val animator2 = ObjectAnimator.ofFloat(dice2, "translationY", -100f, 0f)
        animator2.duration = 500
        animator2.repeatCount = 3
        animator2.repeatMode = ObjectAnimator.REVERSE

        animator1.start()
        animator2.start()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}