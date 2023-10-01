package com.example.mortgagepaymentcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var mortgagePrincipalAmountEditText: EditText
    private lateinit var interestRateEditText: EditText
    private lateinit var amortizationPeriodEditText: EditText
    private lateinit var calculatePaymentsButton: Button
    private lateinit var monthlyPaymentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements by finding them by their IDs
        mortgagePrincipalAmountEditText = findViewById(R.id.mortgagePrincipalAmount)
        interestRateEditText = findViewById(R.id.interestRate)
        amortizationPeriodEditText = findViewById(R.id.amortizationPeriod)
        calculatePaymentsButton = findViewById(R.id.calculatePayments)
        monthlyPaymentTextView = findViewById(R.id.monthlyPayment)

        // Set a click listener for the "Calculate Payments" button
        calculatePaymentsButton.setOnClickListener(View.OnClickListener {
            calculateMonthlyPayment()
        })
    }

    private fun calculateMonthlyPayment() {
        // Retrieve user input from EditText fields
        val principalAmount = mortgagePrincipalAmountEditText.text.toString().toDouble()
        val interestRate = interestRateEditText.text.toString().toDouble() / 100.0
        val amortizationPeriod = amortizationPeriodEditText.text.toString().toInt()

        // Calculate the monthly interest rate and number of payments
        val monthlyInterestRate = interestRate / 12.0
        val numberOfPayments = amortizationPeriod * 12

        // Calculate the monthly mortgage payment using the formula
        val monthlyPayment = principalAmount * (monthlyInterestRate * (1 + monthlyInterestRate).pow(numberOfPayments)) /
                ((1 + monthlyInterestRate).pow(numberOfPayments) - 1)

        // Display the result in the TextView, formatting it to two decimal places
        monthlyPaymentTextView.text = "Monthly Payment: \$${String.format("%.2f", monthlyPayment)}"
    }
}
