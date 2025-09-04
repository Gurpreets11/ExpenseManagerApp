package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.ExpenseMasterDao
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity

class ExpenseRepository(private val expenseDao: ExpenseMasterDao) {

    val allExpenses: LiveData<List<ExpenseMasterEntity>> = expenseDao.getAllExpenses()

    suspend fun insert(expense: ExpenseMasterEntity) {
        expenseDao.insertExpense(expense)
    }

    suspend fun update(expense: ExpenseMasterEntity) {
        expenseDao.updateExpense(expense)
    }

    suspend fun delete(expense: ExpenseMasterEntity) {
        expenseDao.deleteExpense(expense)
    }
}
