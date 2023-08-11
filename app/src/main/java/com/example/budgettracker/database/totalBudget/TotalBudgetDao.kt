package com.example.budgettracker.database.totalBudget

import androidx.room.Dao
import androidx.room.Query
import com.example.budgettracker.model.TotalBudget

@Dao
interface TotalBudgetDao {

    @Query("SELECT * FROM total_budget LIMIT 1")
    suspend fun getTotalBudget(): TotalBudget?


}
