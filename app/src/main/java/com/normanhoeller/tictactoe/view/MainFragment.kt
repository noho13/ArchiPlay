package com.normanhoeller.tictactoe.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.normanhoeller.tictactoe.R
import com.normanhoeller.tictactoe.controller.Controller
import com.normanhoeller.tictactoe.controller.ViewInterface
import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import kotlinx.android.synthetic.main.fr_main.*


/**
 * Created by norman on 07.02.18.
 */
class MainFragment : Fragment(), ViewInterface {


    companion object {
        fun createInstance() = MainFragment()
        const val GAME_STATE = "game_state"
        const val WINNER_TEXT = "winner_text"
    }

    private lateinit var model: Board
    private lateinit var controller: Controller


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init(savedInstanceState?.getCharArray(GAME_STATE))
        if (savedInstanceState != null) {
            updateBoard(model.playStatus)
            tv_result.text = savedInstanceState.getString(WINNER_TEXT)
        }

        tv_0_0.setOnClickListener { controller.clickOnPosition(0, 0) }
        tv_0_1.setOnClickListener { controller.clickOnPosition(0, 1) }
        tv_0_2.setOnClickListener { controller.clickOnPosition(0, 2) }

        tv_1_0.setOnClickListener { controller.clickOnPosition(1, 0) }
        tv_1_1.setOnClickListener { controller.clickOnPosition(1, 1) }
        tv_1_2.setOnClickListener { controller.clickOnPosition(1, 2) }

        tv_2_0.setOnClickListener { controller.clickOnPosition(2, 0) }
        tv_2_1.setOnClickListener { controller.clickOnPosition(2, 1) }
        tv_2_2.setOnClickListener { controller.clickOnPosition(2, 2) }

        tv_reset.setOnClickListener { controller.reset() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putCharArray(GAME_STATE, model.playStatus)
        outState?.putString(WINNER_TEXT, tv_result.text.toString())
    }

    override fun update(mappedPosition: Int?, player: Player?) {
        updateBoard(model.playStatus)
        if (model.checkForWinner(player, mappedPosition)) {
            tv_result.text = "We have a winner: ${player?.name}"
        } else {
            tv_result.text = ""
        }
    }

    private fun init(playState: CharArray?) {
        model = Board(playState ?: CharArray(9))
        controller = Controller(model, this)
    }

    private fun updateBoard(gameState: CharArray) {
        (0 until root.childCount)
                .map { root.getChildAt(it) }
                .forEach {
                    when (it.id) {
                        R.id.tv_0_0 -> tv_0_0.text = gameState[0].toString()
                        R.id.tv_0_1 -> tv_0_1.text = gameState[1].toString()
                        R.id.tv_0_2 -> tv_0_2.text = gameState[2].toString()

                        R.id.tv_1_0 -> tv_1_0.text = gameState[3].toString()
                        R.id.tv_1_1 -> tv_1_1.text = gameState[4].toString()
                        R.id.tv_1_2 -> tv_1_2.text = gameState[5].toString()

                        R.id.tv_2_0 -> tv_2_0.text = gameState[6].toString()
                        R.id.tv_2_1 -> tv_2_1.text = gameState[7].toString()
                        R.id.tv_2_2 -> tv_2_2.text = gameState[8].toString()
                    }
                }


    }
}