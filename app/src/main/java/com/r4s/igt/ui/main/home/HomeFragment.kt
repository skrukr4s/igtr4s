package com.r4s.igt.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.suppot.databind.ext.getViewModel
import com.r4s.igt.BR

import com.r4s.igt.R
import com.r4s.igt.app.App
import com.r4s.igt.app.base.Actions
import com.r4s.igt.app.base.BaseFragment
import com.r4s.igt.app.base.FeedGameAdapter
import com.r4s.igt.app.base.OpenGameDetails
import com.r4s.igt.controllers.models.Game
import com.r4s.igt.databinding.FragmentHomeBinding
import com.r4s.igt.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    override fun getViewModels(): HomeVM? = getViewModel(HomeVM::class)
    override fun getBindingVariable(): Int = BR.vm
    override fun getLayoutId(): Int = R.layout.fragment_home


    override fun onActions(action: Actions) {
        when(action){
            is FeedGameAdapter -> prepareGameAdapter(action.game)
            is OpenGameDetails -> NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_gamedetailsFragment, action.game)
            else -> super.onActions(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParent<MainActivity>()?.getViewModels()?.loginVisibility?.set(true)
    }

    private fun prepareGameAdapter(game: Game){
        val adapter = getViewModels()?.let { GamesAdapter(App.appCtx(), game.data, it) }
        rvGameList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGameList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        getViewModels()?.getGames()
    }
}
