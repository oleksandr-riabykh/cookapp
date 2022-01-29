package com.alex.cooksample.ui.collections.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alex.cooksample.R
import com.alex.cooksample.databinding.FragmentCollectionDetailBinding
import com.alex.cooksample.extensions.navigateTo
import com.alex.cooksample.ui.models.CookCollectionUIModel
import com.alex.cooksample.ui.recipes.RecipesAdapter
import com.alex.cooksample.ui.recipes.detail.RecipeDetailFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionDetailFragment : Fragment() {

    private val viewModel: CollectionDetailViewModel by viewModels()
    private var _binding: FragmentCollectionDetailBinding? = null

    private val binding get() = _binding
    private val recipesAdapter = RecipesAdapter { id ->
        navigateTo(
            R.id.navigation_recipe_details,
            R.id.navigation_collection_details,
            bundle = Bundle().apply {
                putInt(RecipeDetailFragment.ARG_RECIPE_ID, id)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCollectionDetailBinding.inflate(inflater, container, false)
        val root: View? = binding?.root

        arguments?.getInt(ARG_COLLECTION_ID)?.let { viewModel.loadCollection(it) }

        binding?.recipesRecycler?.isNestedScrollingEnabled = false
        binding?.recipesRecycler?.adapter = recipesAdapter
        setupListeners()
        return root
    }

    private fun setupListeners() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is CollectionDetailState.OnLoadRecipesCompleted -> recipesAdapter.setData(state.data)
                is CollectionDetailState.OnLoadCollectionCompleted -> displayData(state.data)
                is CollectionDetailState.OnError -> Toast.makeText(
                    requireContext(),
                    state.error.message,
                    Toast.LENGTH_SHORT
                ).show() // handle errors
            }
        })
    }

    //todo: make it better and avoid duplication
    private fun displayData(collection: CookCollectionUIModel) {
        val previews = collection.previews
        if (previews.isNotEmpty()) {
            Picasso.get().load(previews.first())
                .into(binding?.posterImageView)
        } else {
            binding?.posterImageView?.scaleType = ImageView.ScaleType.CENTER_INSIDE
            binding?.posterImageView?.setImageResource(R.drawable.ic_baseline_no_photography_24)
        }
        binding?.titleTextView?.text = collection.title
        binding?.descriptionTextView?.text = collection.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_COLLECTION_ID = "collection.id"
    }
}