package com.example.android_lab6.presentation.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android_lab6.R
import com.example.android_lab6.presentation.adapter.PersonAdapter
import com.example.android_lab6.presentation.form.FormActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var searchEditText: EditText
    private lateinit var totalRecordsValueTextView: TextView
    private lateinit var filteredRecordsValueTextView: TextView
    private lateinit var filteredRecordsTextView: TextView
    private var adapter = PersonAdapter()
    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            viewModel.onCountTotalPersons()
            viewModel.onSearchFilteredPersons(searchEditText.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        addButton = findViewById(R.id.addButton)
        searchEditText = findViewById(R.id.searchEditText)
        totalRecordsValueTextView = findViewById(R.id.totalRecordsValue)
        filteredRecordsValueTextView = findViewById(R.id.foundRecordsValue)
        filteredRecordsTextView = findViewById(R.id.foundRecordsTextView)
        recycler = findViewById(R.id.recycler)
        recycler.adapter = adapter

        addButton.setOnClickListener {
            launchActivity(FormActivity::class.java)
        }
        searchEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearchFilteredPersons(text.toString())
        }

        viewModel.onLaunch()
        initVM()
    }

    private fun initVM() {
        viewModel.persons.observe(this) { list ->
            adapter.setItems(list)
        }
        viewModel.countPersons.observe(this) { rows ->
            totalRecordsValueTextView.text = rows.toString()
        }
        viewModel.countFilteredPersons.observe(this) { rows ->
            filteredRecordsValueTextView.text = rows.toString()
            val recordsVisibility = rows > 0
            filteredRecordsValueTextView.isVisible = recordsVisibility
            filteredRecordsTextView.isVisible = recordsVisibility
        }
    }

    private fun launchActivity(activityClass: Class<out Activity>) {
        val intent = Intent(this@MainActivity, activityClass)
        someActivityResultLauncher.launch(intent)
    }
}