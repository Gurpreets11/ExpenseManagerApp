package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.IncomeDao
import com.pack.expensemanger.data.storage.entity.IncomeEntity

class IncomeRepository(private val incomeDao: IncomeDao) {

    val allIncome: LiveData<List<IncomeEntity>> = incomeDao.getAllIncome()

    suspend fun insert(income: IncomeEntity) {
        incomeDao.insertIncome(income)
    }

    suspend fun update(income: IncomeEntity) {
        incomeDao.updateIncome(income)
    }

    suspend fun delete(income: IncomeEntity) {
        incomeDao.deleteIncome(income)
    }
}
