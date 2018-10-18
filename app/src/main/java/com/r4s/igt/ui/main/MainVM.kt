package com.r4s.igt.ui.main

import androidx.databinding.ObservableField
import com.r4s.igt.R
import com.r4s.igt.app.base.BaseViewModel
import com.r4s.igt.app.base.Loading
import com.r4s.igt.app.base.Message
import com.r4s.igt.controllers.api.Network
import com.r4s.igt.controllers.api.repository.UserRepository
import com.r4s.igt.controllers.models.Player
import com.r4s.igt.utils.Res
import com.r4s.igt.utils.formatCurrency
import com.r4s.igt.utils.formatDate
import timber.log.Timber


// Created by sebastian with ♥
class MainVM : BaseViewModel() {
    var playerName = ObservableField<String>("")
    var playerBalance = ObservableField<String>("")
    var playerLastLogin = ObservableField<String>("")
    var loginVisibility = ObservableField<Boolean>(true)
    var playerImage = ObservableField<String>("")


    //region Network
    fun getProfileInfo() {
        if(!Network.ForceCacheInterceptor().isNetworkConnected())
            dispatchAction(Message(Res.string(R.string.error_network)))

        dispatchAction(Loading(true))
        UserRepository.getPlayerInfo(onSuccess = {
            Timber.i("API: $it")
            dispatchAction(Loading(false))

            setProfileInfo(it)
        }, onError = {
            dispatchAction(Loading(false))
            dispatchAction(Message(Res.string(R.string.error_try)))
        })
    }
    //endregion

    private fun setProfileInfo(player: Player){
        playerName.set(player.name)
        playerBalance.set(player.balance.formatCurrency("GBP"))
        playerLastLogin.set(player.lastLogindate.formatDate("dd/MM/yyyy'T'HH:mm"))
        playerImage.set(player.avatarLink)
    }
}