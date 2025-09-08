package com.pack.expensemanger.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pack.expensemanger.data.repository.CategoryRepository
import com.pack.expensemanger.data.repository.ExpenseRepository
import com.pack.expensemanger.data.repository.IncomeRepository
import com.pack.expensemanger.data.repository.PaymentMethodRepository
import com.pack.expensemanger.data.repository.PaymentStatusRepository
import com.pack.expensemanger.data.repository.SubCategoryRepository

class AppViewModelFactory(
    private val expenseRepository: ExpenseRepository,
    private val incomeRepository: IncomeRepository,
    private val categoryRepository: CategoryRepository,
    private val subCategoryRepository: SubCategoryRepository,
    private val paymentMethodRepository:  PaymentMethodRepository,
    private val paymentStatusRepository: PaymentStatusRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExpenseViewModel::class.java) -> {
                ExpenseViewModel(expenseRepository) as T
            }
            modelClass.isAssignableFrom(IncomeViewModel::class.java) -> {
                IncomeViewModel(incomeRepository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                CategoryViewModel(categoryRepository) as T
            }
            modelClass.isAssignableFrom(SubCategoryViewModel::class.java) -> {
                SubCategoryViewModel(subCategoryRepository) as T
            }
            modelClass.isAssignableFrom(PaymentMethodViewModel::class.java) -> {
                PaymentMethodViewModel(paymentMethodRepository) as T
            }
            modelClass.isAssignableFrom(PaymentStatusViewModel::class.java) -> {
                PaymentStatusViewModel(paymentStatusRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
