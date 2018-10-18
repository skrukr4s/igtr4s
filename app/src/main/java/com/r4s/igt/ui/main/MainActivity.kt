package com.r4s.igt.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.mobile.suppot.databind.ext.getViewModel
import com.r4s.igt.BR
import com.r4s.igt.R
import com.r4s.igt.app.base.BaseActivity
import com.r4s.igt.databinding.ActivityMainBinding

// Created by sebastian with ♥
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    override fun getViewModels(): MainVM = getViewModel(MainVM::class)
    override fun getBindingVariable(): Int = BR.vm
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragment = NavHostFragment()
        fragment.setGraph(R.navigation.nav_graph_main)

        getViewModels().title.value = getString(R.string.app_name)


        supportFragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .setPrimaryNavigationFragment(fragment)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        getViewModels().getProfileInfo()
    }
}

