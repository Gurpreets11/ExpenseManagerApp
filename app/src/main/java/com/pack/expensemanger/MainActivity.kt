package com.pack.expensemanger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pack.expensemanger.ui.activity.CategoryActivity
import com.pack.expensemanger.ui.activity.ExpenseActivity
import com.pack.expensemanger.ui.activity.IncomeActivity
import com.pack.expensemanger.ui.activity.SubCategoryActivity

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

        val btnIncome = findViewById<Button>(R.id.btnIncome)
        val btnExpense = findViewById<Button>(R.id.btnExpense)
        val btnCategory = findViewById<Button>(R.id.btnCategory)
        val btnSubCategory = findViewById<Button>(R.id.btnSubCategory)

        btnIncome.setOnClickListener {
            startActivity(Intent(this, IncomeActivity::class.java))
        }

        btnExpense.setOnClickListener {
            startActivity(Intent(this, ExpenseActivity::class.java))
        }

        btnCategory.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }

        btnSubCategory.setOnClickListener {
            startActivity(Intent(this, SubCategoryActivity::class.java))
        }
    }
}