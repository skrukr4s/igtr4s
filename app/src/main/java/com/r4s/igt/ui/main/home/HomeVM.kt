package com.r4s.igt.ui.main.home;

import android.os.Bundle
import com.r4s.igt.R
import com.r4s.igt.app.base.*
import com.r4s.igt.controllers.api.Network
import com.r4s.igt.controllers.api.repository.UserRepository
import com.r4s.igt.controllers.models.GameData
import com.r4s.igt.utils.Res

class HomeVM : BaseViewModel(), IGameAdapter {

    //region Network
    fun getGames(){
        if(!Network.ForceCacheInterceptor().isNetworkConnected())
            dispatchAction(Message(Res.string(R.string.error_network)))

        dispatchAction(Loading(true))
        UserRepository.getGameData(onSuccess = {
            dispatchAction(Loading(false))
            dispatchAction(FeedGameAdapter(it))
        }, onError = {
            dispatchAction(Loading(false))
            dispatchAction(Message(Res.string(R.string.error_try)))
        })
    }
    //endregion

    //region Actions
    override fun onGameItemClicked(gameData: GameData) {
        var bundle = Bundle()
        bundle.putParcelable("game", gameData)
        dispatchAction(OpenGameDetails(bundle))
    }
    //endregion
}