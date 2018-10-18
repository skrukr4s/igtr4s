package com.r4s.igt.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.r4s.igt.BR
import com.r4s.igt.R
import com.r4s.igt.controllers.models.GameData
import kotlinx.android.synthetic.main.item_game.view.*

/** Created by sebastian with ♥ on 17.10.2018 **/
interface IGameAdapter {
    fun onGameItemClicked(gameData: GameData)
}

class GamesAdapter(val context: Context, var gameList: List<GameData>, var iGameAdapter: IGameAdapter) : RecyclerView.Adapter<RecyclerView.ViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.iGameAdapter = iGameAdapter
        return GameAdapterVH(LayoutInflater.from(context).inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gameHolder = holder as GameAdapterVH
        gameHolder.binding?.setVariable(BR.ad, gameHolder)
        gameHolder.binding?.executePendingBindings()

        gameHolder.bind(gameList[position])
    }

    inner class GameAdapterVH(view: View): RecyclerView.ViewHolder(view){
        val binding: ViewDataBinding? = DataBindingUtil.bind(view)
        var rootView = view
        lateinit var gameData: GameData

        fun bind(game: GameData){
            gameData = game
            rootView.tvGameTitle.text = game.name
        }

        fun onItemClicked(){
            iGameAdapter.onGameItemClicked(gameData)
        }
    }
}