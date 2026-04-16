package com.example.studentregistrationformapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etStudentId: EditText
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etAge: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbFootball: CheckBox
    private lateinit var cbCricket: CheckBox
    private lateinit var cbBasketball: CheckBox
    private lateinit var cbBadminton: CheckBox
    private lateinit var spinnerCountry: Spinner
    private lateinit var btnDatePicker: Button
    private lateinit var tvSelectedDate: TextView
    private lateinit var btnSubmit: Button
    private lateinit var btnReset: Button

    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views using findViewById
        etStudentId = findViewById(R.id.etStudentId)
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etAge = findViewById(R.id.etAge)
        rgGender = findViewById(R.id.rgGender)
        cbFootball = findViewById(R.id.cbFootball)
        cbCricket = findViewById(R.id.cbCricket)
        cbBasketball = findViewById(R.id.cbBasketball)
        cbBadminton = findViewById(R.id.cbBadminton)
        spinnerCountry = findViewById(R.id.spinnerCountry)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnReset = findViewById(R.id.btnReset)

        // Date Picker Dialog handling
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = "Selected Date: $selectedDate"
            }, year, month, day)
            datePickerDialog.show()
        }

        // Submit Button handling
        btnSubmit.setOnClickListener {
            if (validateInputs()) {
                val data = collectData()
                Toast.makeText(this, data, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Please complete all required fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        // Reset Button handling
        btnReset.setOnClickListener {
            resetForm()
        }
    }

    private fun validateInputs(): Boolean {
        val studentId = etStudentId.text.toString().trim()
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val ageStr = etAge.text.toString().trim()

        // Check for empty fields
        if (studentId.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || ageStr.isEmpty()) {
            return false
        }

        // Age validation
        val age = ageStr.toIntOrNull() ?: 0
        if (age <= 0) {
            return false
        }

        // Email validation
        if (!email.contains("@")) {
            return false
        }

        // Gender selection validation
        if (rgGender.checkedRadioButtonId == -1) {
            return false
        }

        // Date selection validation
        if (selectedDate.isEmpty()) {
            return false
        }

        return true
    }

    private fun collectData(): String {
        val studentId = etStudentId.text.toString().trim()
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        // Password is not usually displayed but collected
        val age = etAge.text.toString().trim()

        val selectedGenderId = rgGender.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedGenderId)
        val gender = radioButton.text.toString()

        val sportsList = mutableListOf<String>()
        if (cbFootball.isChecked) sportsList.add("Football")
        if (cbCricket.isChecked) sportsList.add("Cricket")
        if (cbBasketball.isChecked) sportsList.add("Basketball")
        if (cbBadminton.isChecked) sportsList.add("Badminton")
        val sports = if (sportsList.isEmpty()) "None" else sportsList.joinToString(", ")

        val country = spinnerCountry.selectedItem.toString()

        return "ID: $studentId\n" +
                "Name: $fullName\n" +
                "Email: $email\n" +
                "Age: $age\n" +
                "Gender: $gender\n" +
                "Sports: $sports\n" +
                "Country: $country\n" +
                "DOB: $selectedDate"
    }

    private fun resetForm() {
        etStudentId.text.clear()
        etFullName.text.clear()
        etEmail.text.clear()
        etPassword.text.clear()
        etAge.text.clear()
        rgGender.clearCheck()
        cbFootball.isChecked = false
        cbCricket.isChecked = false
        cbBasketball.isChecked = false
        cbBadminton.isChecked = false
        spinnerCountry.setSelection(0)
        selectedDate = ""
        tvSelectedDate.text = ""
    }
}
