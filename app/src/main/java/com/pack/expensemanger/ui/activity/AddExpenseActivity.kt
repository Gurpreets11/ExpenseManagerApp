package com.pack.expensemanger.ui.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity
import com.pack.expensemanger.databinding.ActivityAddExpenseBinding
import com.pack.expensemanger.viewmodel.CategoryViewModel
import com.pack.expensemanger.viewmodel.ExpenseViewModel
import com.pack.expensemanger.viewmodel.PaymentMethodViewModel
import com.pack.expensemanger.viewmodel.PaymentStatusViewModel
import com.pack.expensemanger.viewmodel.SubCategoryViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private val viewModel: ExpenseViewModel by viewModels()


    private val categoryViewModel: CategoryViewModel by viewModels()
    private val subCategoryViewModel: SubCategoryViewModel by viewModels()
    private val paymentMethodViewModel: PaymentMethodViewModel by viewModels()
    private val paymentStatusViewModel: PaymentStatusViewModel by viewModels()

    private var selectedDateTime: Long = System.currentTimeMillis()
    private var selectedCategoryId: Int = -1
    private var selectedSubCategoryId: Int = -1

    private var selectedPaymentMethodId: Int = -1
    private var selectedPaymentStatusId: Int = -1

    private lateinit var selectedCategoryName: String
    private lateinit var selectedSubCategoryName: String
    private lateinit var paymentMethod: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdowns()
        setupDateTimePicker()

        binding.btnSaveExpense.setOnClickListener {
            saveExpense()
        }
    }

    private fun setupDropdowns() {
        /* // Payment Methods
         val paymentMethods = listOf("Cash", "Card", "UPI", "Wallet")
         val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, paymentMethods)
         binding.etPaymentMethod.setAdapter(paymentAdapter)

         // Payment Status
         val paymentStatuses = listOf("Paid", "Pending", "Failed")
         val statusAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, paymentStatuses)
         binding.etPaymentStatus.setAdapter(statusAdapter)

          val categories = listOf("Food", "Transport", "Shopping")
         val subCategories = listOf("Snacks", "Taxi", "Clothes")

         val catAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
         binding.etCategory.setAdapter(catAdapter)

         val subCatAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subCategories)
         binding.etSubCategory.setAdapter(subCatAdapter)

         // You can fetch IDs of categories/subcategories from DB instead of using index
         binding.etCategory.setOnItemClickListener { _, _, position, _ ->
             selectedCategoryId = position + 1
             selectedCategoryName = categories[position]
         }
         binding.etSubCategory.setOnItemClickListener { _, _, position, _ ->
             selectedSubCategoryId = position + 1
             selectedSubCategoryName = subCategories[position]
         }*/


        // 🔹 Fetch Payment Methods
        paymentMethodViewModel.allPaymentMethods.observe(this) { methods ->
            val methodNames = methods.map { it.methodName }
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, methodNames)
            binding.etPaymentMethod.setAdapter(adapter)

            binding.etPaymentMethod.setOnItemClickListener { _, _, position, _ ->
                selectedPaymentMethodId = methods[position].id
                // paymentMethod= methods[position].
            }
        }

        // 🔹 Fetch Payment Status
        paymentStatusViewModel.allPaymentStatuses.observe(this) { paymentStatus ->
            val statusNames = paymentStatus.map { it.statusName }
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, statusNames)
            binding.etPaymentStatus.setAdapter(adapter)

            binding.etPaymentStatus.setOnItemClickListener { _, _, position, _ ->
                selectedPaymentStatusId = paymentStatus[position].id
            }
        }

        // 🔹 Fetch Categories

        /*categoryViewModel.allCategories.observe(this) { categories ->

        }*/

        categoryViewModel.allCategories.observe(this) { categories ->
            val categoryNames = categories.map { it.name }
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categoryNames)
            binding.etCategory.setAdapter(adapter)

            binding.etCategory.setOnItemClickListener { _, _, position, _ ->
                selectedCategoryId = categories[position].id
                selectedCategoryName = categories[position].name
                // when category changes → update subcategories
                subCategoryViewModel.getSubCategoriesByCategory(selectedCategoryId)
                    .observe(this) { subCategories ->
                        val subCatNames = subCategories.map { it.name }
                        val subAdapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_dropdown_item_1line,
                            subCatNames
                        )
                        binding.etSubCategory.setAdapter(subAdapter)

                        binding.etSubCategory.setOnItemClickListener { _, _, subPos, _ ->
                            selectedSubCategoryId = subCategories[subPos].id
                            selectedSubCategoryName = subCategories[position].name
                        }
                    }
            }
        }

    }


    private fun setupDateTimePicker() {
        val calendar = Calendar.getInstance()
        updateDateTimeText(calendar.timeInMillis)

        binding.tvExpenseDateTime.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    TimePickerDialog(
                        this,
                        { _, hour, minute ->
                            calendar.set(year, month, day, hour, minute)
                            selectedDateTime = calendar.timeInMillis
                            updateDateTimeText(selectedDateTime)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateTimeText(timestamp: Long) {
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        binding.tvExpenseDateTime.text = sdf.format(Date(timestamp))
    }

    private fun saveExpense() {
        val amountText = binding.etAmount.text.toString().trim()

        val notes = binding.etNotes.text.toString().trim()

        if (amountText.isEmpty() || selectedCategoryId == -1 || selectedSubCategoryId == -1 ||
            selectedPaymentMethodId == -1 || selectedPaymentStatusId == -1
        ) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val expense = ExpenseMasterEntity(
            id = 0,
            title = "$selectedCategoryName-$selectedSubCategoryName-$paymentMethod",
            amount = amountText.toDouble(),
            expenseDateTime = selectedDateTime,
            paymentMethod = selectedPaymentMethodId,
            categoryId = selectedCategoryId,
            category = selectedCategoryName,
            subCategoryId = selectedSubCategoryId,
            subCategory = selectedSubCategoryName,
            paymentStatus = selectedPaymentStatusId,
            notes = notes
        )


        viewModel.insert(expense)
        Toast.makeText(this, "Expense saved successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}