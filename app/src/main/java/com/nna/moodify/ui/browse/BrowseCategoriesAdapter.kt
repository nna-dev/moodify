package com.nna.moodify.ui.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nna.moodify.databinding.ItemBrowseCategoryBinding
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.getDefaultImageUri

class BrowseCategoriesAdapter :
    ListAdapter<Category, BrowseCategoriesAdapter.ViewHolder>(CategoryDiffUtil) {

    class ViewHolder(binding: ItemBrowseCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.categoryTitle
        val image = binding.categoryImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBrowseCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.title.text = "$position. ${category.name}"
        Glide.with(holder.itemView).load(category.icons.getDefaultImageUri()).into(holder.image)
    }
}

private object CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}