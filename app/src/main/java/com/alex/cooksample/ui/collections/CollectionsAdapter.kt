package com.alex.cooksample.ui.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.cooksample.R
import com.alex.cooksample.databinding.ItemCollectionBinding
import com.alex.cooksample.ui.models.CookCollectionUIModel

class CollectionAdapter(
    private val onClickItem: (item: CookCollectionUIModel) -> Unit = {}
) : RecyclerView.Adapter<CollectionViewHolder>() {

    private var mListOfItems: ArrayList<CookCollectionUIModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CollectionViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
    )

    fun setData(items: List<CookCollectionUIModel>) {
        mListOfItems.clear()
        mListOfItems.addAll(items)
        notifyItems()
    }

    override fun getItemCount(): Int = mListOfItems.size

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = mListOfItems[position]
        holder.binding.model = item
        holder.bind(item) { onClickItem(it) }
    }

    private fun notifyItems() = mListOfItems.forEachIndexed { index, _ -> notifyItemChanged(index) }
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
