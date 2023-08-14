package com.example.budgettracker.repository

import com.example.budgettracker.database.expense.ExpenseDao
import com.example.budgettracker.model.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ExpenseRepository(val expenseDao: ExpenseDao) {

    private val job = SupervisorJob()
    private val expenseScope = CoroutineScope(job + Dispatchers.IO)


    suspend fun insertIExpense(expense: Expense) {
        expenseScope.launch {
            expenseDao.insert(expense)
        }
    }

    suspend fun getAllExpenses(): List<Expense> {
        return expenseScope.async {
            expenseDao.getAll()
        }.await()
    }

    suspend fun deleteAll() {
        expenseScope.launch {
            expenseDao.deleteAll()
        }
    }

    suspend fun getTotalExpenses(): Double {
        return expenseScope.async {
            expenseDao.getTotalExpenses()
        }.await()
    }
}