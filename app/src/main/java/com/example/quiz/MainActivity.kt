package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val TAG = "yjsoon.MainActivity"

    val questions = listOf(
        Question("There are 9 planets in our solar system",
            false),
        Question("SST stands for School of Science and Technology",
            true),
        Question("You can create iOS apps with Android Studio",
            true)
        )

    var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val questionTextView = findViewById<TextView>(R.id.questionTextView)

        questionTextView.text = questions[currentQuestion].statement

        trueButton.setOnClickListener {
            checkCorrect(true, it)
            nextButton.visibility = View.VISIBLE
        }

        falseButton.setOnClickListener {
            checkCorrect(false, it)
            nextButton.visibility = View.VISIBLE
        }

        // next button should...
        // increment currentQuestion
        // set questionTextView
        // hide itself
        nextButton.setOnClickListener {
            currentQuestion++

            if (currentQuestion >= questions.size) {
                Log.d(TAG, "We've reached the end of the questions")
                val intent = Intent(this, CongratsActivity::class.java).apply {
                    putExtra("score", "0") // incomplete! 
                }
                startActivity(intent)
            } else {
                questionTextView.text = questions[currentQuestion].statement
                nextButton.visibility = View.INVISIBLE
            }
        }

    }

    private fun checkCorrect(correctness: Boolean, it: View) {
        var message = "Wrong!"
        if (questions[currentQuestion].correctness == correctness) {
            message = "Correct!"
        }
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
//      Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT).show()
    }
}