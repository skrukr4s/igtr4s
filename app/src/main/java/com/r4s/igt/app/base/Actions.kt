package com.r4s.igt.app.base

import android.os.Bundle
import com.r4s.igt.controllers.models.Game

sealed class Actions

//Base
object Finish : Actions()

object BackPress : Actions()
data class Message(val content: String = "") : Actions()
data class Loading(val content: Boolean = false) : Actions()

data class FeedGameAdapter(val game: Game) : Actions()
data class OpenGameDetails(val game: Bundle) : Actions()


