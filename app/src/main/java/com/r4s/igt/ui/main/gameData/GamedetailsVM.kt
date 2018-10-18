package com.r4s.igt.ui.main.gameData;

import android.os.Bundle
import androidx.databinding.ObservableField
import com.r4s.igt.app.base.BaseViewModel
import com.r4s.igt.controllers.models.GameData
import com.r4s.igt.utils.formatCurrency
import com.r4s.igt.utils.formatDate

class GamedetailsVM : BaseViewModel() {
    var gameName = ObservableField<String>("")
    var gameJackpot = ObservableField<String>("")
    var gameDate = ObservableField<String>("")

    fun handleArguments(arguments: Bundle?){
        var game = arguments?.getParcelable<GameData>("game")
        gameName.set(game?.name)
        gameJackpot.set(game?.jackpot.formatCurrency("GBP"))
        gameDate.set(game?.date.formatDate("yyyy-MM-dd'T'HH:mm:ssX"))
    }
}