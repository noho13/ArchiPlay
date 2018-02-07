package com.normanhoeller.tictactoe.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.normanhoeller.tictactoe.R
import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import kotlinx.android.synthetic.main.fr_main.*


/**
 * Created by norman on 07.02.18.
 */
class MainFragment : Fragment() {

    companion object {
        fun createInstance() = MainFragment()
    }

    private lateinit var board: Board
    private lateinit var playerOne: Player
    private lateinit var playerTwo: Player
    private var currentSymbol: Char? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupGame()

        tv_0_0.setOnClickListener { clickOnPosition(0, 0) }
        tv_0_1.setOnClickListener { clickOnPosition(0, 1) }
        tv_0_2.setOnClickListener { clickOnPosition(0, 2) }

        tv_1_0.setOnClickListener { clickOnPosition(1, 0) }
        tv_1_1.setOnClickListener { clickOnPosition(1, 1) }
        tv_1_2.setOnClickListener { clickOnPosition(1, 2) }

        tv_2_0.setOnClickListener { clickOnPosition(2, 0) }
        tv_2_1.setOnClickListener { clickOnPosition(2, 1) }
        tv_2_2.setOnClickListener { clickOnPosition(2, 2) }

        tv_reset.setOnClickListener { reset() }
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
        currentSymbol = null
        setupGame()
    }

    private fun setupGame() {
        board = Board(this)
        playerOne = Player("Norman", 'X')
        playerTwo = Player("Stephi", 'O')
    }

    private fun clickOnPosition(row: Int, col: Int) {
        val mappedPosition = board.mapToPosition(row, col)
        if (currentSymbol == null || currentSymbol == playerTwo.symbol) {
            currentSymbol = playerOne.symbol
            playerOne.timesPlayed++
            board.storePosition(playerOne, mappedPosition)
        } else {
            currentSymbol = playerTwo.symbol
            playerTwo.timesPlayed++
            board.storePosition(playerTwo, mappedPosition)
        }
    }

    fun updateView(pos: Int, player: Player) {
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

        if (board.checkForWinner(player, pos)) {
            tv_result.text = "We have a winner: ${player.name}"
        }
    }
}