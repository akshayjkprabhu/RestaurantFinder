package com.ajp.dinein.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ajp.dinein.R
import com.ajp.dinein.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
	
	
	companion object {
		const val TAG = "HomeActivity"
	}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		val binder = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
		
		supportFragmentManager
				.beginTransaction()
				.replace(binder.container.id, SearchFragment.newInstance(), null)
				.commit()
	}
}