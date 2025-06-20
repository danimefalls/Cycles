package com.example.cycles

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var inputEpsilon: EditText
    private lateinit var computeButton: Button
    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEpsilon = findViewById(R.id.inputEpsilon)
        computeButton = findViewById(R.id.computeButton)
        resultView = findViewById(R.id.resultView)

        computeButton.setOnClickListener {
            val epsilonText = inputEpsilon.text.toString()

            if (epsilonText.isEmpty()) {
                resultView.text = "Введите значение точности!"
                return@setOnClickListener
            }

            val epsilon = epsilonText.toDoubleOrNull()
            if (epsilon == null || epsilon <= 0) {
                resultView.text = "Неверный формат точности. Введите положительное число."
                return@setOnClickListener
            }

            val result = computeSum(epsilon)
            resultView.text = result
        }
    }

    private fun computeSum(epsilon: Double): String {
        var sum = 0.0
        var term: Double
        var n = 1
        var iterations = 0

        do {
            term = 1.0 / (n * n)
            sum += term
            n++
            iterations++
        } while (abs(term) >= epsilon)

        return """
            Сумма: $sum
            Последнее слагаемое: $term
            Количество итераций: $iterations
        """.trimIndent()
    }
}

