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
    }

    private lateinit var model: Board
    private lateinit var controller: Controller


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init(savedInstanceState?.getCharArray(GAME_STATE))

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
    }

    override fun update(mappedPosition: Int?, player: Player?) {
        updateView(mappedPosition, player)

    }

    private fun reset() {
        tv_0_0.text = ""
        tv_0_1.text = ""
        tv_0_2.text = ""

        tv_1_0.text = ""
        tv_1_1.text = ""
        tv_1_2.text = ""

        tv_2_0.text = ""
        tv_2_1.text = ""
        tv_2_2.text = ""

        tv_result.text = ""
    }

    private fun init(playState: CharArray?) {
        model = Board(playState ?: CharArray(9))
        controller = Controller(model, this)
    }

    private fun updateView(pos: Int?, player: Player?) {
        if (pos == null || player == null) {
            reset()
            return
        }
        val symbol = player.symbol
        when (pos) {
            0 -> tv_0_0.text = symbol.toString()
            1 -> tv_0_1.text = symbol.toString()
            2 -> tv_0_2.text = symbol.toString()

            3 -> tv_1_0.text = symbol.toString()
            4 -> tv_1_1.text = symbol.toString()
            5 -> tv_1_2.text = symbol.toString()

            6 -> tv_2_0.text = symbol.toString()
            7 -> tv_2_1.text = symbol.toString()
            8 -> tv_2_2.text = symbol.toString()
        }
        if (model.checkForWinner(player, pos)) {
            tv_result.text = "We have a winner: ${player.name}"
        }
    }

    fun restoreUIafterConfigChange(gameState: CharArray) {

    }
}