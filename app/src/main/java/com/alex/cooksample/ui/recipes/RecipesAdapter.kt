package com.alex.cooksample.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.cooksample.R
import com.alex.cooksample.databinding.ItemRecipeBinding
import com.alex.cooksample.ui.models.RecipeUIModel

class RecipesAdapter(
    private val onClickItem: (itemId: Int) -> Unit = {}
) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private var mListOfItems: ArrayList<RecipeUIModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
    )

    fun setData(items: List<RecipeUIModel>) {
        val diffCallback = RecipesDiffCallback(mListOfItems, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mListOfItems.clear()
        mListOfItems.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = mListOfItems.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = mListOfItems[position]
        holder.binding.model = item
        holder.bind(item) { onClickItem(it) }
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

    class RecipesDiffCallback(
        private val oldList: List<RecipeUIModel>,
        private val newList: List<RecipeUIModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id === newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, value, name) = oldList[oldPosition]
            val (_, value1, name1) = newList[newPosition]

            return name == name1 && value == value1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }

    }
}
