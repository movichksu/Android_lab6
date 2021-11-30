package com.example.android_lab6.presentation.form

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android_lab6.R
import com.example.android_lab6.data.entity.Person

class FormActivity : AppCompatActivity() {

    private lateinit var viewModel: FormViewModel
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var commentEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        viewModel = ViewModelProvider(this)[FormViewModel::class.java]

        nameEditText = findViewById(R.id.nameEditText)
        commentEditText = findViewById(R.id.commentEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        saveButton.setOnClickListener {
            if (!validateFields()) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.error_toast),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            finishIntentWithResult()
        }
    }

    private fun finishIntentWithResult() {
        val person = Person(
            name = nameEditText.text.toString(),
            surname = surnameEditText.text.toString(),
            comment = commentEditText.text.toString()
        )
        viewModel.addPerson(person)
        setResult(RESULT_OK)
        finish()
    }

    private fun validateFields(): Boolean {
        return when {
            nameEditText.text.isNullOrBlank() -> false
            surnameEditText.text.isNullOrBlank() -> false
            commentEditText.text.isNullOrBlank() -> false
            else -> true
        }
    }

}