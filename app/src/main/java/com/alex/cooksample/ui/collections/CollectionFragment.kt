package com.alex.cooksample.ui.collections

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
import com.alex.cooksample.ui.collections.detail.CollectionDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    private val viewModel: CollectionViewModel by viewModels()
    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding
    private val collectionAdapter = CollectionAdapter { collection ->
        navigateTo(
            R.id.navigation_collection_details,
            R.id.navigation_collections,
            bundle = Bundle().apply {
                collection.id?.let { putInt(CollectionDetailFragment.ARG_COLLECTION_ID, it) }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View? = binding?.root

        binding?.rootRecycler?.adapter = collectionAdapter
        setupListeners()
        return root
    }

    private fun setupListeners() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is CollectionState.OnLoadCompleted -> collectionAdapter.setData(state.data)
                is CollectionState.OnError -> Toast.makeText(
                    requireContext(),
                    state.error.message,
                    Toast.LENGTH_SHORT
                ).show() // handle errors
            }
        })
        viewModel.showLoadingIndicator.observe(viewLifecycleOwner, { showIndicator ->
            if (showIndicator) binding?.progress?.show() else binding?.progress?.hide()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}