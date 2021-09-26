package com.ajp.dinein.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajp.dinein.core.base.BaseFragment
import com.ajp.dinein.databinding.FragmentSearchResultsBinding
import com.ajp.dinein.domain.model.Restaurant
import com.ajp.dinein.presentation.viewmodel.RestaurantSearchViewModel
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<RestaurantSearchViewModel>() {
	
	companion object {
		const val TAG = "SearchFragment"
		
		fun newInstance() = SearchFragment()
	}
	
	private lateinit var binder : FragmentSearchResultsBinding
	private lateinit var searchAdapter : SearchAdapter
	
	private val viewModel : RestaurantSearchViewModel by inject()
	
	override fun inflateContent(inflater : LayoutInflater, container : FrameLayout) : View {
		binder = FragmentSearchResultsBinding.inflate(inflater, container, true)
		return binder.root
	}
	
	override fun viewModel() : RestaurantSearchViewModel {
		return viewModel
	}
	
	override fun observeLiveData(viewModel : RestaurantSearchViewModel) {
		with(viewModel) {
			restaurantResults.observe(viewLifecycleOwner, { onSearchResults(it) })
		}
	}
	
	private fun onSearchResults(list : List<Restaurant>?) {
		if (list == null || list.isEmpty()) {
			return
		}
		searchAdapter.updateList(list)
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initViews()
		fetchRestaurantList()
	}
	
	private fun initViews() {
		with(binder.searchResults) {
			this@SearchFragment.searchAdapter = SearchAdapter()
			this.adapter = this@SearchFragment.searchAdapter
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		}
	}
	
	private fun fetchRestaurantList() {
		viewModel.searchRestaurantFor("")
	}
}