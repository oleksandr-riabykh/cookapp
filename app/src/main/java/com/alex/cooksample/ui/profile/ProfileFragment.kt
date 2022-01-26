package com.alex.cooksample.ui.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex.cooksample.R
import com.alex.cooksample.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View? = binding?.root

        binding?.smileImage?.setOnClickListener {
            val snackbar =
                Snackbar.make(it, R.string.greeting, Snackbar.LENGTH_SHORT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                snackbar.setTextColor(resources.getColor(R.color.orange, resources.newTheme()))
            }
            snackbar.show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}