package com.ajp.dinein.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.ajp.dinein.databinding.FragmentBaseBinding

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {
	
	private lateinit var mBaseBinder : FragmentBaseBinding
	
	companion object {
		const val TAG = "BaseFragment"
	}
	
	override fun onCreateView(
			inflater : LayoutInflater,
			container : ViewGroup?,
			savedInstanceState : Bundle?
	) : View? {
		mBaseBinder = FragmentBaseBinding.inflate(inflater, container, false)
		initToolbar()
		inflateContent(inflater, container = mBaseBinder.content)
		observeLiveData()
		return mBaseBinder.root
	}
	
	private fun initToolbar() {
		with(mBaseBinder.toolBar) {
			searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
				override fun onQueryTextChange(p0 : String?) : Boolean {
					searchTextChanged(p0)
					return false
				}
				
				override fun onQueryTextSubmit(p0 : String?) : Boolean {
					searchTextChanged(p0)
					return false
				}
			})
		}
	}
	
	private fun observeLiveData() {
		val viewModel = viewModel() ?: return
		with(viewModel) {
			uiHelperLiveData.observe(viewLifecycleOwner, { onUIStateChanged(it) })
		}
		observeLiveData(viewModel)
	}
	
	private fun onUIStateChanged(uiStateModel : UIStateModel?) {
		mBaseBinder.uiState = uiStateModel
	}
	
	abstract fun inflateContent(inflater : LayoutInflater, container : FrameLayout) : View?
	
	abstract fun viewModel() : ViewModel?
	
	abstract fun observeLiveData(viewModel : ViewModel)
	
	abstract fun searchTextChanged(searchText : String?)
}

