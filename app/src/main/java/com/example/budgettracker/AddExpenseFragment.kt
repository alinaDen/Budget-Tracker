package com.example.budgettracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope

import com.example.budgettracker.databinding.FragmentAddExpenseBinding
import com.example.budgettracker.model.Expense
import com.example.budgettracker.repository.ExpenseRepository

import kotlinx.android.synthetic.main.fragment_add_expense.amountEditText

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddExpenseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddExpenseBinding
    private  val expenseRepository = ExpenseRepository(MyBudgetApp.INSTANCE.database.expenseDao())
    private var selectedCategory: String = ""
    private var selectedCategoryIndex: Byte = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddExpenseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = view.findViewById(R.id.category_spinner)
        val items = listOf("Food", "Transport", "Medicine","Cosmetics","Clothes","Vacations","Education","Sport","Restaurants",
        "Entertainment","Vehicle","Other","Savings")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCategory = items[position]
                selectedCategoryIndex = (position + 1).toByte()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        binding.saveButton.setOnClickListener {
            val amount = amountEditText.run {
                val textVar = text.toString()
                text?.clear()
                textVar.toDouble()
            }
            


            lifecycleScope.launch(Dispatchers.IO) {
                expenseRepository.insertIExpense(Expense(selectedCategoryIndex, amount, ""))
            }
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance("", ""))
                .commit()

        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddExpenseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}