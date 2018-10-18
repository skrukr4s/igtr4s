package com.r4s.igt.ui.main.gameData

import android.os.Bundle
import android.view.View
import com.mobile.suppot.databind.ext.getViewModel
import com.r4s.igt.BR
import com.r4s.igt.R
import com.r4s.igt.app.base.BaseFragment
import com.r4s.igt.databinding.FragmentGamedetailsBinding
import com.r4s.igt.ui.main.MainActivity

class GamedetailsFragment : BaseFragment<FragmentGamedetailsBinding, GamedetailsVM>() {

    override fun getViewModels(): GamedetailsVM? = getViewModel(GamedetailsVM::class)
    override fun getBindingVariable(): Int = BR.vm
    override fun getLayoutId(): Int = R.layout.fragment_gamedetails


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParent<MainActivity>()?.getViewModels()?.loginVisibility?.set(false)

        getViewModels()?.handleArguments(arguments)
    }
}
