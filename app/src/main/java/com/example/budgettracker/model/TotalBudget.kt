package com.example.budgettracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total_budget")
data class TotalBudget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val remainingBudget: Double=0.0
)
