package com.example.budgettracker

import android.app.Application
import com.example.budgettracker.database.AppDatabase


class MyBudgetApp: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: MyBudgetApp
    }

}