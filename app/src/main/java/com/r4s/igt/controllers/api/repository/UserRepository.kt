package com.r4s.igt.controllers.api.repository

import com.r4s.igt.controllers.api.Network
import com.r4s.igt.controllers.models.Game
import com.r4s.igt.controllers.models.Player
import kotlinx.coroutines.experimental.launch
import timber.log.Timber

/** Created by sebastian with ♥ on 14.09.2018 **/

object UserRepository{

    fun getGameData(onSuccess: (Game) -> Unit, onError: () -> Unit){
        launch {
            var response = Network.create().getGameData().await()
            if(response.isSuccessful){
                response.body()?.let { onSuccess(it) }
            }else{
                Timber.e("Error: ${response.code()} ${response.message()}")
            }
        }
    }

    fun getPlayerInfo( onSuccess: (Player) -> Unit, onError: () -> Unit){
        launch {
            var response = Network.create().getPlayerData().await()
            if(response.isSuccessful){
                response.body()?.let { onSuccess(it) }
            }else{
                Timber.e("Error: ${response.code()} ${response.message()}")
            }
        }
    }
}