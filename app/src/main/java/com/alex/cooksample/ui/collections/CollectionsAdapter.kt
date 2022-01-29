package com.alex.cooksample.ui.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.cooksample.R
import com.alex.cooksample.databinding.ItemCollectionBinding
import com.alex.cooksample.ui.models.CookCollectionUIModel

class CollectionAdapter(
    private val onClickItem: (item: CookCollectionUIModel) -> Unit = {}
) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private var mListOfItems: ArrayList<CookCollectionUIModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CollectionViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
    )

    fun setData(items: List<CookCollectionUIModel>) {
        val diffCallback = CollectionsDiffCallback(mListOfItems, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mListOfItems.clear()
        mListOfItems.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = mListOfItems.size

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = mListOfItems[position]
        holder.binding.model = item
        holder.bind(item) { onClickItem(it) }
    }

    class CollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemCollectionBinding = ItemCollectionBinding.bind(view)
        fun bind(
            item: CookCollectionUIModel,
            onClickItem: (item: CookCollectionUIModel) -> Unit,
        ) {
            binding.root.setOnClickListener { onClickItem(item) }
        }
    }

    class CollectionsDiffCallback(private val oldList: List<CookCollectionUIModel>, private val newList: List<CookCollectionUIModel>) : DiffUtil.Callback() {

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

