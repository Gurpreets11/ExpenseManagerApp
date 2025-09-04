package com.pack.expensemanger.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pack.expensemanger.ExpensesManagerApp
import com.pack.expensemanger.databinding.ActivityCategoryBinding
import com.pack.expensemanger.ui.adapter.CategoryAdapter
import com.pack.expensemanger.ui.dialog.AddCategoryDialog
import com.pack.expensemanger.viewmodel.CategoryViewModel


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var adapter: CategoryAdapter

    private lateinit var categoryViewModel: CategoryViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CategoryAdapter()
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategories.adapter = adapter

        // Get ViewModel
        val app = application as ExpensesManagerApp

        categoryViewModel =
            ViewModelProvider(this, app.appViewModelFactory)[CategoryViewModel::class.java]


        // Observe categories
        categoryViewModel.allCategories.observe(this, Observer { categories ->
            categories?.let { adapter.submitList(it) }
        })

        // Add button
        binding.fabAddCategory.setOnClickListener {
            val dialog = AddCategoryDialog { category ->
                categoryViewModel.insert(category)
            }
            dialog.show(supportFragmentManager, "AddCategoryDialog")
        }
    }
}
