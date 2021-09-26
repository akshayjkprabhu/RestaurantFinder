package com.ajp.dinein.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
		inflateContent(inflater, container = mBaseBinder.content)
		observeLiveData()
		return mBaseBinder.root
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
}