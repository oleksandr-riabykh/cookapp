package com.alex.cooksample.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alex.cooksample.R
import com.alex.cooksample.databinding.FragmentListBinding
import com.alex.cooksample.extensions.navigateTo
import com.alex.cooksample.ui.recipes.detail.RecipeDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()
    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding
    private val recipesAdapter = RecipesAdapter { id ->
        navigateTo(
            R.id.action_recipes_to_details,
            bundle = Bundle().apply {
                putInt(RecipeDetailFragment.ARG_RECIPE_ID, id)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View? = binding?.root
        binding?.rootRecycler?.adapter = recipesAdapter
        setupListeners()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadRecipes()
    }

    private fun setupListeners() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RecipesState.OnLoadCompleted -> {
                    recipesAdapter.setData(state.data)
                }
                is RecipesState.OnError -> {
                    Toast.makeText(
                        requireContext(),
                        state.error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                } // handle errors
            }
        }
        viewModel.showLoadingIndicator.observe(viewLifecycleOwner) { showIndicator ->
            binding?.swipeRefreshLayout?.isRefreshing  = showIndicator
        }
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            viewModel.loadRecipes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}