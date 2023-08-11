package com.example.budgettracker.database.income

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.budgettracker.model.Income


@Dao
interface IncomeDao {
    @Query("SELECT * FROM incomes")
    fun getAll(): List<Income>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(income: Income)

    @Query("UPDATE incomes SET amount = :newAmount WHERE id = :id")
    fun updateAmount(id: Int, newAmount: Double)

    @Query("UPDATE incomes SET description = :newDescription WHERE id = :id")
    fun updateDescription(id: Int, newDescription: String)
    @Query("UPDATE expenses SET type = :newType WHERE id = :id")
    fun updateType(id: Int, newType: Byte)

    @Query("DELETE FROM incomes WHERE id = :id")
    fun deleteExpense(id: Int)

    @Query("DELETE FROM incomes")
    fun deleteAll()
}