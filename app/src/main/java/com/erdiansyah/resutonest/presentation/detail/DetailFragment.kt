package com.erdiansyah.resutonest.presentation.detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.erdiansyah.resutonest.BuildConfig
import com.erdiansyah.resutonest.R
import com.erdiansyah.resutonest.core.data.State
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.databinding.FragmentDetailBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val detailViewModel: DetailViewModel by activityViewModels()
    private var isFavorite by Delegates.notNull<Boolean>()
    private lateinit var idResto: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        idResto = DetailFragmentArgs.fromBundle(arguments as Bundle).id
        isFavorite = false

        lifecycleScope.launch {
            detailViewModel.restaurantDetail(idResto).flowWithLifecycle(lifecycle).collect {restaurant ->
                when(restaurant) {
                    is State.Loading -> _binding?.progressBar?.visibility = View.VISIBLE
                    is State.Success -> {
                        _binding?.progressBar?.visibility = View.GONE
                        val resto = restaurant.data
                        if (resto != null) {
                            _binding?.apply {
                                Glide.with(requireContext())
                                    .load(Uri.parse(BuildConfig.BASE_URL_PICTURE+resto.pictureId))
                                    .error(R.drawable.img_resto)
                                    .into(ivResto)
                                tvRestaurant.text = resto.name
                                tvCity.text = resto.city
                                tvRating.text = resto.rating.toString()
                                tvDesc.text = resto.description
                                for (drink in resto.menus.drinks) {
                                    addMenuToChip(drink.name)
                                }
                                for (food in resto.menus.foods) {
                                    addMenuToChip(food.name)
                                }
                                _binding?.fabFavorite?.setOnClickListener {
                                    isFavorite = !isFavorite
                                    if (isFavorite) {
                                        detailViewModel.insertFavorite(
                                            Restaurant(
                                                pictureId = resto.pictureId,
                                                rating = resto.rating,
                                                id = resto.id,
                                                city = resto.city,
                                                description = resto.description,
                                                name = resto.name
                                            )
                                        )
                                        Toast.makeText(requireContext(),getString(R.string.add_to_favorite), Toast.LENGTH_SHORT).show()
                                    } else {
                                        detailViewModel.deleteNonFav(resto.id)
                                        Toast.makeText(requireContext(),getString(R.string.delete_from_favorite), Toast.LENGTH_SHORT).show()
                                    }
                                    lifecycleScope.launch {
                                        val count = detailViewModel.checkFavorite(idResto)
                                        isFavorite = if (count > 0) {
                                            _binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
                                            true
                                        } else {
                                            _binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unfavorite))
                                            false
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
        _binding?.icBack?.setOnClickListener {
            findNavController().popBackStack()
        }
        return _binding?.root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val count = detailViewModel.checkFavorite(idResto)
            if (count > 0) {
                _binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
                isFavorite = true
            } else {
                _binding?.fabFavorite?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unfavorite))
                isFavorite = false
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private  fun addMenuToChip(text: String) {
        val chip = Chip(requireContext())
        chip.text = text
        _binding?.chipMenu?.addView(chip)
    }
}