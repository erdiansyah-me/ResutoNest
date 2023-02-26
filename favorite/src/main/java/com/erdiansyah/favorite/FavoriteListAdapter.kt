package com.erdiansyah.favorite

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdiansyah.resutonest.core.domain.model.Restaurant
import com.erdiansyah.resutonest.databinding.HomeItemListBinding

class FavoriteListAdapter (private val onItemClick: (String) -> Unit):RecyclerView.Adapter<FavoriteListAdapter.ListViewHolder>() {
        private var listRestaurantItem = ArrayList<Restaurant>()


        fun setData(newListData: List<Restaurant>?) {
            if (newListData == null) return
            listRestaurantItem.clear()
            listRestaurantItem.addAll(newListData)
            notifyDataSetChanged()
        }

        inner class ListViewHolder(var binding: HomeItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val binding = HomeItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val data = listRestaurantItem[position]
            holder.binding.apply {
                Glide.with(holder.itemView.context)
                    .load(Uri.parse("https://restaurant-api.dicoding.dev/images/small/${data.pictureId}"))
                    .into(ivResto)
                tvRestaurant.text = data.name
                tvCity.text = data.city
                tvRating.text = data.rating.toString()
                root.setOnClickListener {
                    onItemClick(data.id)
                }
            }
        }

        override fun getItemCount() = listRestaurantItem.size
}