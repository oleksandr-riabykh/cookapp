package com.alex.cooksample.ui.collections.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alex.cooksample.R
import com.alex.cooksample.databinding.FragmentCollectionDetailBinding
import com.alex.cooksample.extensions.navigateTo
import com.alex.cooksample.ui.recipes.RecipesAdapter
import com.alex.cooksample.ui.recipes.detail.RecipeDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionDetailFragment : Fragment() {

    private val viewModel: CollectionDetailViewModel by viewModels()
    private var _binding: FragmentCollectionDetailBinding? = null

    private val binding get() = _binding
    private val recipesAdapter = RecipesAdapter { id ->
        navigateTo(
            R.id.action_collection_to_recipe,
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

        binding?.recipesRecycler?.isNestedScrollingEnabled = false
        binding?.recipesRecycler?.adapter = recipesAdapter
        setupListeners()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun setupListeners() {
        viewModel.collectionUIModel.observe(viewLifecycleOwner) { collection ->
            binding?.model = collection
        }
        viewModel.recipesUIModel.observe(viewLifecycleOwner) { recipes ->
            recipesAdapter.setData(recipes)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(
                requireContext(),
                error.localizedMessage ?: error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.showLoadingIndicator.observe(viewLifecycleOwner) { showIndicator ->
            binding?.swipeRefreshLayout?.isRefreshing = showIndicator
        }
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        arguments?.getInt(ARG_COLLECTION_ID)?.let {
            viewModel.loadCollection(it)
            viewModel.loadRecipes(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_COLLECTION_ID = "collection.id"
    }
}