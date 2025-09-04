package com.pack.expensemanger.ui.activity

import com.pack.expensemanger.ExpensesManagerApp
import com.pack.expensemanger.R
import com.pack.expensemanger.data.storage.entity.IncomeEntity
import com.pack.expensemanger.ui.adapter.IncomeAdapter
import com.pack.expensemanger.viewmodel.IncomeViewModel


import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class IncomeActivity : AppCompatActivity() {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var adapter: IncomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerIncome)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddIncome)

        adapter = IncomeAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Get ViewModel
        val app = application as ExpensesManagerApp
        incomeViewModel =
            ViewModelProvider(this, app.appViewModelFactory)[IncomeViewModel::class.java]

        // Observe data
        incomeViewModel.allIncome.observe(this) { incomeList ->
            adapter.updateList(incomeList)
        }

        // Add new income
        fabAdd.setOnClickListener {
            showAddIncomeDialog()
        }
    }

    private fun showAddIncomeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_income, null)
        val edtSource = dialogView.findViewById<EditText>(R.id.edtSource)
        val edtAmount = dialogView.findViewById<EditText>(R.id.edtAmount)
        val edtDate = dialogView.findViewById<EditText>(R.id.edtDate)

        AlertDialog.Builder(this)
            .setTitle("Add Income")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val source = edtSource.text.toString()
                val amount = edtAmount.text.toString().toDoubleOrNull()
                val date = edtDate.text.toString()

                if (source.isNotEmpty() && amount != null && date.isNotEmpty()) {
                    val income = IncomeEntity(
                        id = 0, // auto-generated
                        title = source,
                        amount = amount,
                        date = date, notes = "", categoryId = 0
                    )
                    incomeViewModel.insert(income)
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
