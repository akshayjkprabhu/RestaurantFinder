package com.ajp.dinein.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajp.dinein.databinding.ItemRestaurantBinding
import com.ajp.dinein.domain.model.IListItem
import com.ajp.dinein.domain.model.Restaurant

class SearchAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	private var searchItemList : List<IListItem>? = null
	
	
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return RestaurantViewHolder(ItemRestaurantBinding.inflate(inflater, parent, false))
	}
	
	override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
		searchItemList?.get(position)?.let {
			if (holder is RestaurantViewHolder) {
				holder.bindData(it as? Restaurant)
			}
		}
	}
	
	override fun getItemCount() = searchItemList?.size ?: 0
	
	/**
	 * This is not useful right now
	 */
	override fun getItemViewType(position : Int) : Int {
		return searchItemList?.get(position)?.itemType()?.id ?: 0
	}
	
	fun updateList(list : List<Restaurant>?) {
		this.searchItemList = list
		notifyDataSetChanged()
	}
	
	inner class RestaurantViewHolder(private val binder : ItemRestaurantBinding) : RecyclerView.ViewHolder(binder.root) {
		
		fun bindData(restaurant : Restaurant?) {
			binder.data = restaurant
		}
	}
}