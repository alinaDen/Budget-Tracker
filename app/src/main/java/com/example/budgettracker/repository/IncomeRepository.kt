package com.example.budgettracker.repository

import com.example.budgettracker.database.income.IncomeDao
import com.example.budgettracker.model.Income
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class IncomeRepository(val incomeDao: IncomeDao) {

    private val job = SupervisorJob()
    private val incomeScope = CoroutineScope(job + Dispatchers.IO)


    suspend fun insertIncome(income: Income) {
        incomeScope.launch {
            incomeDao.insert(income)
        }
    }

    suspend fun getAllIncomes(): List<Income> {
        return incomeScope.async {
            incomeDao.getAll()
        }.await()
    }

    suspend fun deleteAll() {
        incomeScope.launch {
            incomeDao.deleteAll()
        }
    }

    suspend fun getTotalIncome(): Double {
        return incomeScope.async {
            incomeDao.getTotalIncome()
        }.await()
    }
}