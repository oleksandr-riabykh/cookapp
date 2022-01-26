package com.alex.cooksample.ui.recipes.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.cooksample.R
import com.alex.cooksample.databinding.ItemRecipeBinding
import com.alex.cooksample.databinding.ItemStepBinding
import com.alex.cooksample.ui.models.RecipeUIModel
import com.alex.cooksample.ui.models.StepUIModel

class StepsAdapter(private var mListOfItems: List<StepUIModel>): RecyclerView.Adapter<StepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StepViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_step, parent, false)
    )

    override fun getItemCount(): Int = mListOfItems.size

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val item = mListOfItems[position]
        holder.binding.model = item
    }
}

class StepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ItemStepBinding = ItemStepBinding.bind(view)
}
