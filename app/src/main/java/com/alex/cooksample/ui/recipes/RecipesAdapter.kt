package com.alex.cooksample.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.cooksample.R
import com.alex.cooksample.databinding.ItemRecipeBinding
import com.alex.cooksample.ui.models.RecipeUIModel

class RecipesAdapter(
    private val onClickItem: (itemId: Int) -> Unit = {}
) : RecyclerView.Adapter<RecipesViewHolder>() {

    private var mListOfItems: ArrayList<RecipeUIModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
    )

    fun setData(items: List<RecipeUIModel>) {
        mListOfItems.clear()
        mListOfItems.addAll(items)
        notifyItems()
    }

    override fun getItemCount(): Int = mListOfItems.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = mListOfItems[position]
        holder.binding.model = item
        holder.bind(item) { onClickItem(it) }
    }

    private fun notifyItems() = mListOfItems.forEachIndexed { index, _ -> notifyItemChanged(index) }
}

class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ItemRecipeBinding = ItemRecipeBinding.bind(view)
    fun bind(
        item: RecipeUIModel,
        onClickItem: (itemId: Int) -> Unit,
    ) {
        binding.root.setOnClickListener { item.id?.let(onClickItem) }
    }
}
