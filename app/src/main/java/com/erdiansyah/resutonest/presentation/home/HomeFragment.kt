package com.erdiansyah.resutonest.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val listAdapter = RestaurantListAdapter {
            val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            toDetailFragment.id = it
            findNavController().navigate(toDetailFragment)
        }

        lifecycleScope.launch {
            homeViewModel.restaurant.flowWithLifecycle(lifecycle).collect {
                when (it) {
                    is State.Loading -> _binding?.progressBar?.visibility = View.VISIBLE
                    is State.Success -> {
                        _binding?.progressBar?.visibility = View.GONE
                        _binding?.rvListHome?.visibility = View.VISIBLE
                        _binding?.tvError?.visibility = View.GONE
                        listAdapter.setData(it.data)
                    }
                    is State.Error -> {
                        _binding?.progressBar?.visibility = View.GONE
                        _binding?.rvListHome?.visibility = View.INVISIBLE
                        _binding?.tvError?.visibility = View.VISIBLE
                    }
                    else -> {
                        _binding?.tvError?.visibility = View.VISIBLE
                    }
                }
            }
        }

        _binding?.rvListHome?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }

        _binding?.favoriteButton?.setOnClickListener {
            val toFavorite = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
            findNavController().navigate(toFavorite)
        }

        return _binding?.root
    }
}