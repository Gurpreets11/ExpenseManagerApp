package com.pack.expensemanger.ui.adapter

import com.pack.expensemanger.R
import com.pack.expensemanger.data.storage.entity.SubCategoryEntity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class SubCategoryAdapter :
    ListAdapter<SubCategoryEntity, SubCategoryAdapter.SubCategoryViewHolder>(SubCategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sub_category, parent, false)
        return SubCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SubCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSubCategoryName: TextView = itemView.findViewById(R.id.tvSubCategoryName)
        private val tvParentCategoryName: TextView =
            itemView.findViewById(R.id.tvParentCategoryName)

        fun bind(subCategory: SubCategoryEntity) {
            tvSubCategoryName.text = subCategory.name
            tvParentCategoryName.text = "Category Name: ${subCategory.categoryId}"
        }
    }

    class SubCategoryDiffCallback : DiffUtil.ItemCallback<SubCategoryEntity>() {
        override fun areItemsTheSame(
            oldItem: SubCategoryEntity,
            newItem: SubCategoryEntity
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SubCategoryEntity,
            newItem: SubCategoryEntity
        ): Boolean =
            oldItem == newItem
    }
}
