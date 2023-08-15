package com.example.budgettracker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.databinding.FragmentMainBinding
import com.example.budgettracker.model.Expense
import com.example.budgettracker.repository.ExpenseAdapter
import com.example.budgettracker.repository.ExpenseRepository
import com.example.budgettracker.repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainBinding
     var expenseListener: AddExpenseListener? = null
    var incomeListener: AddEIncomeListener? = null
    private lateinit var expenseAdapter: ExpenseAdapter
    private  val expenseRepository = ExpenseRepository(MyBudgetApp.INSTANCE.database.expenseDao())
    private  val incomeRepository= IncomeRepository(MyBudgetApp.INSTANCE.database.incomeDao())


    interface AddExpenseListener{
        fun addExpense()
    }
    interface AddEIncomeListener{
        fun addIncome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseListener = requireActivity() as? AddExpenseListener
        incomeListener = requireActivity() as? AddEIncomeListener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       binding= FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            addExpenseButton.setOnClickListener{
                expenseListener?.addExpense()
//                binding.addExpenseButton.setOnClickListener {
//                    expenseListener?.addExpense()

//                }


            }
            binding.expenseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            expenseAdapter = ExpenseAdapter()
            binding.expenseRecyclerView.adapter = expenseAdapter
            lifecycleScope.launch(Dispatchers.IO) {
                val expense = expenseRepository.getAllExpenses()
                withContext(Dispatchers.Main) {
                    expenseAdapter.setExpenseList(expense)
                    expenseAdapter.notifyDataSetChanged()
                }
            }
            addIncomeButton.setOnClickListener {
                incomeListener?.addIncome()
            }
        }


        lifecycleScope.launch(Dispatchers.IO) {
            val totalExpenses = expenseRepository.getTotalExpenses()?: 0.00

            val formattedExpense = String.format("%.2f", totalExpenses)

            withContext(Dispatchers.Main) {
                binding.decreaseAmount.text  = formattedExpense

            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val totalIncomes = incomeRepository.getTotalIncome()?: 0.00
            val formattedIncome = String.format("%.2f", totalIncomes)

            withContext(Dispatchers.Main) {
                binding.increaseAmount.text  = formattedIncome

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val totalIncomes = incomeRepository.getTotalIncome() ?: 0.00
            val totalExpenses = expenseRepository.getTotalExpenses() ?: 0.00
            val balance = (totalIncomes - totalExpenses)
            val formattedBalance = String.format("%.2f", balance)

            withContext(Dispatchers.Main) {
                binding.balanceValue.text  = formattedBalance

            }
        }



    }

    companion object {

        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}