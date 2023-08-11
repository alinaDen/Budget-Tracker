package com.example.budgettracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.budgettracker.databinding.ActivityMainBinding
import com.example.budgettracker.repository.ExpenseRepository

class MainActivity : AppCompatActivity(),MainFragment.AddExpenseListener,MainFragment.AddEIncomeListener{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainFragment = MainFragment.newInstance("", "")
       // mainFragment.listener = this
        supportFragmentManager.beginTransaction()
            .add(R.id.container, mainFragment)
            .commit()
    }

    override fun addExpense(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddExpenseFragment.newInstance("",""))
            .commit()
    }
    override fun addIncome(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddIncomeFragment.newInstance("",""))
            .commit()
    }
}