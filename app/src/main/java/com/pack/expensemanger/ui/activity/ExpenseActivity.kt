package com.pack.expensemanger.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pack.expensemanger.ExpensesManagerApp
import com.pack.expensemanger.R
import com.pack.expensemanger.ui.adapter.ExpenseAdapter
import com.pack.expensemanger.ui.dialog.AddExpenseDialog
import com.pack.expensemanger.viewmodel.ExpenseViewModel

class ExpenseActivity : AppCompatActivity() {


    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val recyclerView =
            findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewExpenses)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddExpense)

        adapter = ExpenseAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Get ViewModel
        val app = application as ExpensesManagerApp
        expenseViewModel =
            ViewModelProvider(this, app.appViewModelFactory)[ExpenseViewModel::class.java]

        // Observe data
        expenseViewModel.allExpenses.observe(this) { expensesList ->
            adapter.updateList(expensesList)
        }

        // Add new income
        fabAdd.setOnClickListener {
            //showAddIncomeDialog()

           /* val dialog = AddExpenseDialog { expense ->
                expenseViewModel.insert(expense)
            }
            dialog.show(supportFragmentManager, "AddExpenseDialog")*/

            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

    }
}