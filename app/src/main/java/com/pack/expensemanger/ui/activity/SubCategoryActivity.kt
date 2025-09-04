package com.pack.expensemanger.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.pack.expensemanger.ExpensesManagerApp
import com.pack.expensemanger.R
import com.pack.expensemanger.databinding.ActivitySubCategoryBinding
import com.pack.expensemanger.ui.adapter.SubCategoryAdapter
import com.pack.expensemanger.viewmodel.CategoryViewModel
import com.pack.expensemanger.viewmodel.SubCategoryViewModel


 import androidx.activity.viewModels
 import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pack.expensemanger.ui.dialog.AddSubCategoryDialog


class SubCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubCategoryBinding
    private lateinit var adapter: SubCategoryAdapter

    private lateinit var categoryViewModel: CategoryViewModel

    private lateinit var subCategoryViewModel: SubCategoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = SubCategoryAdapter()
        binding.recyclerViewSubCategories.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSubCategories.adapter = adapter

        // Get ViewModel
        val app = application as ExpensesManagerApp

        categoryViewModel =
            ViewModelProvider(this, app.appViewModelFactory)[CategoryViewModel::class.java]

        subCategoryViewModel =
            ViewModelProvider(this, app.appViewModelFactory)[SubCategoryViewModel::class.java]


        // Observe subcategories
        subCategoryViewModel.getAllSubCategories().observe(this, Observer { subCategories ->
            subCategories?.let { adapter.submitList(it) }
        })

        // Add button
        binding.fabAddSubCategory.setOnClickListener {
            categoryViewModel.allCategories.observe(this) { categories ->
                val dialog = AddSubCategoryDialog(categories) { subCategory ->
                    subCategoryViewModel.insert(subCategory)
                }
                dialog.show(supportFragmentManager, "AddSubCategoryDialog")
            }
        }
    }
}
