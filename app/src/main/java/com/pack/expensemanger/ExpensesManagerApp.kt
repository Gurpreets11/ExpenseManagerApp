package com.pack.expensemanger

import android.app.Application
import com.pack.expensemanger.data.repository.*
import com.pack.expensemanger.data.storage.AppDatabase
import com.pack.expensemanger.viewmodel.AppViewModelFactory


class ExpensesManagerApp : Application() {

    // Database instance
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

    // Repositories
    val expenseRepository by lazy { ExpenseRepository(database.expenseDao()) }
    val incomeRepository by lazy { IncomeRepository(database.incomeDao()) }
    val categoryRepository by lazy { CategoryRepository(database.categoryDao()) }
    val subCategoryRepository by lazy { SubCategoryRepository(database.subCategoryDao()) }

    // Single ViewModelFactory
    val appViewModelFactory by lazy {
        AppViewModelFactory(
            expenseRepository,
            incomeRepository,
            categoryRepository,
            subCategoryRepository
        )
    }
}
