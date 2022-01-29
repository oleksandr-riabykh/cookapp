package com.alex.cooksample.ui.recipes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alex.cooksample.R
import com.alex.cooksample.databinding.FragmentRecipeDetailBinding
import com.alex.cooksample.extensions.dateFormat
import com.alex.cooksample.ui.models.RecipeUIModel
import com.alex.cooksample.utils.DATE_SERVER_INPUT_FORMAT
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val detailViewModel: RecipeDetailViewModel by viewModels()
    private var _binding: FragmentRecipeDetailBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        val root: View? = binding?.root

        binding?.stepsRecycler?.isNestedScrollingEnabled = false
        setupListeners()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun setupListeners() {
        detailViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RecipeDetailState.OnLoadCompleted -> updateViews(state.data)
                is RecipeDetailState.OnError -> Toast.makeText(
                    requireContext(),
                    state.error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        detailViewModel.showLoadingIndicator.observe(viewLifecycleOwner) { showIndicator ->
            binding?.swipeRefreshLayout?.isRefreshing = showIndicator
        }
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        arguments?.getInt(ARG_RECIPE_ID)
            ?.let { detailViewModel.loadRecipe(it) }
    }

    private fun updateViews(data: RecipeUIModel) {
        if (data.imageUrl.isNotEmpty()) {
            Picasso.get().load(data.imageUrl)
                .into(binding?.posterImageView)
        } else {
            binding?.posterImageView?.scaleType = ImageView.ScaleType.CENTER_CROP
            binding?.posterImageView?.setImageResource(R.drawable.ic_menu_place_holder)
        }
        binding?.titleTextView?.text = data.title
        binding?.ingridientsTextView?.text = data.ingredients?.joinToString(
            prefix = "***\n* ",
            postfix = "\n***",
            separator = "\n* "
        )
        binding?.descriptionTextView?.text = data.story
        binding?.dateTextView?.text = data.publishedAt.dateFormat(
            DATE_SERVER_INPUT_FORMAT,
            getString(R.string.date_recipe_details)
        )
        binding?.stepsRecycler?.adapter = StepsAdapter(data.steps ?: listOf())
        data.user?.photo?.let {
            if (it.isNotEmpty()) {
                Picasso.get().load(it)
                    .placeholder(R.drawable.ic_hello)
                    .into(binding?.userAvatarImage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_RECIPE_ID = "recipe.id"
    }
}