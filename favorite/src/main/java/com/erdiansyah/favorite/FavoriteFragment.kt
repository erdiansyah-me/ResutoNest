package com.erdiansyah.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.erdiansyah.favorite.databinding.FragmentFavoriteBinding
import com.erdiansyah.resutonest.app.di.FavoriteModule
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var factory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .dependency(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModule::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }
    private val favoriteViewModel: FavoriteViewModel by viewModels{factory}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val listAdapter = FavoriteListAdapter {
            val toDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment()
            toDetailFragment.id = it
            findNavController().navigate(toDetailFragment)
        }
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        lifecycleScope.launch {
            favoriteViewModel.favorite.flowWithLifecycle(lifecycle).collect {
                if (it.isNotEmpty()) {
                    binding.tvEmpty.visibility = View.GONE
                    listAdapter.setData(it)
                } else {
                    binding.tvEmpty.visibility = View.VISIBLE
                    listAdapter.setData(it)
                }
            }
        }
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}