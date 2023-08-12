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
import com.example.budgettracker.databinding.FragmentAddIncomeBinding
import com.example.budgettracker.model.Expense
import com.example.budgettracker.model.Income
import com.example.budgettracker.repository.ExpenseRepository
import com.example.budgettracker.repository.IncomeRepository
import kotlinx.android.synthetic.main.fragment_add_expense.amountEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddIncomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddIncomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddIncomeBinding
    private  val incomeRepository = IncomeRepository(MyBudgetApp.INSTANCE.database.incomeDao())
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
        binding = FragmentAddIncomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = view.findViewById(R.id.category_spinner)
        val items = listOf("Salary", "Presents", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items.sorted())
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
                incomeRepository.insertIncome(Income(1,selectedCategoryIndex, amount, ""))
            }
        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            val income = incomeRepository.getAllIncomes()
//            withContext(Dispatchers.Main) {
//                binding.textView.text = expense.toString()
//            }
//        }

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddIncomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}