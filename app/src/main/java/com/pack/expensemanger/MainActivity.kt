package com.pack.expensemanger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.pack.expensemanger.data.storage.AppDatabase
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity
import com.pack.expensemanger.data.storage.entity.PaymentStatusEntity
import com.pack.expensemanger.ui.activity.CategoryActivity
import com.pack.expensemanger.ui.activity.ExpenseActivity
import com.pack.expensemanger.ui.activity.IncomeActivity
import com.pack.expensemanger.ui.activity.SubCategoryActivity
import com.pack.expensemanger.viewmodel.CategoryViewModel
import com.pack.expensemanger.viewmodel.PaymentMethodViewModel
import com.pack.expensemanger.viewmodel.PaymentStatusViewModel
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var paymentMethodViewModel: PaymentMethodViewModel
    private lateinit var paymentStatusViewModel: PaymentStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get ViewModel
        val app = application as ExpensesManagerApp

        paymentMethodViewModel = ViewModelProvider(this, app.appViewModelFactory)[PaymentMethodViewModel::class.java]
        paymentStatusViewModel = ViewModelProvider(this, app.appViewModelFactory)[PaymentStatusViewModel::class.java]

        // Prefill data
        prefillPaymentMethods()
        prefillPaymentStatuses()


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

    private fun prefillPaymentMethods() {
        Executors.newSingleThreadExecutor().execute {
            if (paymentMethodViewModel.count == 0) {  // only insert if table empty
                paymentMethodViewModel.insert(PaymentMethodEntity(1, "Cash"))
                paymentMethodViewModel.insert(PaymentMethodEntity(2, "Card"))
                paymentMethodViewModel.insert(PaymentMethodEntity(3, "UPI"))
                paymentMethodViewModel.insert(PaymentMethodEntity(4, "Wallet"))
            }
        }
    }

    private fun prefillPaymentStatuses() {
        Executors.newSingleThreadExecutor().execute {
            if (paymentStatusViewModel.count == 0) {
                paymentStatusViewModel.insert(PaymentStatusEntity(1, "Paid"))
                paymentStatusViewModel.insert(PaymentStatusEntity(2, "Pending"))
                paymentStatusViewModel.insert(PaymentStatusEntity(3, "Failed"))
            }
        }
    }
}